package org.swallow.service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class BlockchainService {
    private final Web3j web3j;

    public BlockchainService(String rpcUrl) {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
    }

    public BigDecimal getEthBalance(String address) throws Exception {
        var response = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        return Convert.fromWei(response.getBalance().toString(), Convert.Unit.ETHER);
    }

    public void close() {
        web3j.shutdown();
    }
}
