package org.swallow.model;

import org.web3j.crypto.*;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Map;

public class AccountService {
    public Map<String,String> create(){
        // Using Web3j
// This creates a new credentials object (Private Key + Public Key)
        ECKeyPair keyPair = null;
        try {
            keyPair = Keys.createEcKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        Credentials credentials = Credentials.create(keyPair);

        System.out.println("Private Key: " + credentials.getEcKeyPair().getPrivateKey());
        System.out.println("Address: " + credentials.getAddress());
        return Map.of();
    }

    public static String createMnemonic(){
        byte[] initialEntropy = new byte[16];
        new SecureRandom().nextBytes(initialEntropy);

        // 2. Convert entropy to a 12-word mnemonic phrase
        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
        System.out.println("Your 12-word Seed Phrase: " + mnemonic);

        // 3. Convert that phrase into a Seed (a long byte array)
//        byte[] seed = MnemonicUtils.generateSeed(mnemonic, null);
        return mnemonic;
    }

    public static void main(String[] args) throws CipherException, IOException {
//        String mnemonic = "candy maple cake sugar pudding cream honey rich smooth crumble sweet treat";
//        Bip39Wallet wallet = WalletUtils.generateBip39WalletFromMnemonic("password", mnemonic, new File("C:\\Users\\Administrator\\Desktop\\web3\\KeyStore"));
//        System.out.println(wallet.getMnemonic());


    }


}
