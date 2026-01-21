package org.swallow;

import org.swallow.core.Web3jIdentityManager;
import org.swallow.service.BlockchainService;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
// Initialize our components
        Web3jIdentityManager identityManager = new Web3jIdentityManager();
        BlockchainService blockchainService = new BlockchainService("https://ethereum.publicnode.com");

        try {
            // 1. Create a wallet
            String myWords = identityManager.generateNewMnemonic();
            System.out.println("New Seed Phrase: " + myWords);

            // 2. Derive the first account
            Credentials account = identityManager.deriveAccount(myWords, 0);
            System.out.println("Main Address: " + account.getAddress());

            // 3. Check its balance
            BigDecimal balance = blockchainService.getEthBalance(account.getAddress());
            System.out.println("Balance: " + balance + " ETH");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            blockchainService.close();
        }
    }
}