package org.swallow.core;

import org.web3j.crypto.Credentials;

import java.util.List;

public interface IdentityManager {
    /**
     * Generates a brand new 12-word mnemonic phrase.
     * @return A space-separated string of 12 words.
     */
    String generateNewMnemonic();

    /**
     * Derives a specific account from a mnemonic.
     * @param mnemonic The 12-word seed phrase.
     * @param index The account index (0, 1, 2...).
     * @return Credentials containing the Private Key and Address.
     */
    Credentials deriveAccount(String mnemonic, int index);

    /**
     * Bulk derives a list of accounts.
     * @param mnemonic The 12-word seed phrase.
     * @param count Number of accounts to generate.
     * @return A list of Credentials.
     */
    List<Credentials> deriveMultipleAccounts(String mnemonic, int count);
}
