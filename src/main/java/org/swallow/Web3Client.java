package org.swallow;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Private Key: 67944012621024802047499818360918238289142116714557997340863046302966582941841
 * Address: 0x670959eb6db78e1fbbd3980f3fd7b8af2ec924eb
 */
public class Web3Client {
    public static void main(String[] args) throws IOException {

        // Using Web3j
// This creates a new credentials object (Private Key + Public Key)
//        ECKeyPair keyPair = Keys.createEcKeyPair();
//        Credentials credentials = Credentials.create(keyPair);
//
//        System.out.println("Private Key: " + credentials.getEcKeyPair().getPrivateKey());
//        System.out.println("Address: " + credentials.getAddress());

// 1. Connect to the Ethereum Mainnet (or a Testnet)
        Web3j web3 = Web3j.build(new HttpService("https://ethereum.publicnode.com"));

        // 2. Define the address you want to check
        String address = "0x670959eb6db78e1fbbd3980f3fd7b8af2ec924eb";

        // 3. Send the request
        EthGetBalance balanceResponse = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        if (balanceResponse.hasError()){
                System.out.println("Error from Node: " + balanceResponse.getError().getMessage());
        } else {
            System.out.println("Balance: " + balanceResponse.getBalance());
        }


        // 4. Get the raw value (in Wei)
        java.math.BigInteger wei = balanceResponse.getBalance();



        // 5. Convert Wei to ETH (human-readable)
        BigDecimal ether = Convert.fromWei(wei.toString(), Convert.Unit.ETHER);

        System.out.println("Address: " + address);
        System.out.println("Balance: " + ether + " ETH");
    }
}
