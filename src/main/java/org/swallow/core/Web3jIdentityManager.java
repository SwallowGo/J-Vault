package org.swallow.core;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Web3jIdentityManager implements IdentityManager {
    @Override
    public String generateNewMnemonic() {
        byte[] initialEntropy = new byte[16];
        new SecureRandom().nextBytes(initialEntropy);

        // 2. Convert entropy to a 12-word mnemonic phrase
        return MnemonicUtils.generateMnemonic(initialEntropy);
    }

    @Override
    public Credentials deriveAccount(String mnemonic, int index) {
        // 1. Create the Seed
        byte[] seed = MnemonicUtils.generateSeed(mnemonic, null);

        // 2. Create Master Key
        Bip32ECKeyPair masterKey = Bip32ECKeyPair.generateKeyPair(seed);

        // 3. Define the path (m/44'/60'/0'/0/0)
        int[] path = {44 | Bip32ECKeyPair.HARDENED_BIT,
                60 | Bip32ECKeyPair.HARDENED_BIT,
                Bip32ECKeyPair.HARDENED_BIT,
                0,
                index};
        // 5. Derive the specific Child Key
        Bip32ECKeyPair childKey = Bip32ECKeyPair.deriveKeyPair(masterKey, path);

        // 6. Now you have the Credentials!
        return Credentials.create(childKey);
    }

    @Override
    public List<Credentials> deriveMultipleAccounts(String mnemonic, int count) {
        ArrayList<Credentials> credentialsList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            credentialsList.add(deriveAccount(mnemonic, i));
        }
        return credentialsList;
    }
}
