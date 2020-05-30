package Nano;

import java.security.*;
import java.util.LinkedList;
import java.lang.Exception;
import java.util.Random;

public class account {
    //地址即是账户的公钥
    private final String privateKey;

    {
        privateKey = generateString.getStringRandom(16);
    }

    //私钥
    private final String address;

    {
        address = StringUtil.applySha256(this.privateKey);
    }
    String signature;
    private int balance;//余额
    //由地址哈希计算出私钥


    private LinkedList<Block> ownBlockchain=new LinkedList<>();

    //开户函数,首个账户需要自己开户
    void openTransaction()  {
        if(!ownBlockchain.isEmpty())
            throw new RuntimeException();
        else{
        openBlock a=new openBlock();
        //随机给定初始的余额
        Random rand=new Random();
        int i=rand.nextInt(10)+1;
        a.work=100*i;
        this.balance=a.work;
        //创世账户减少相应数量
        genesis.genesisBalance-=a.work;
        a.address=this.address;
        a.representative=this.address;
        a.hash=a.calculateHash();
        this.ownBlockchain.add(a);
        genesis.genesisAccount.add(this.address);
        }


    }
    //由别的账户发来的交易进行开户
    void openTransaction(account b,int amount){
        if(!ownBlockchain.isEmpty())
            throw new RuntimeException();
        else{
            openBlock a=new openBlock();
            a.work=amount;
            this.balance=a.work;
            a.address=this.address;
            a.representative=this.address;
            a.hash=a.calculateHash();
            this.ownBlockchain.add(a);
            genesis.genesisAccount.add(this.address);
        }
    }
    //发送交易函数
    void sendTransaction(account des,int amount){
        if (ownBlockchain.isEmpty())
            throw new RuntimeException();
        else{
            //将sendBlock添加到发送方的区块链中
            sendBlock a=new sendBlock();
            a.previous=this.ownBlockchain.getLast().hash;
            a.destination=des.address;
            a.balance=this.balance;
            a.work=amount;
            a.signature=this.signature;
            a.hash=a.calculateHash();
            this.ownBlockchain.add(a);
            this.balance-=amount;
           //如果目标账户未开户，对目标账户中使用此交易开户
            if (des.ownBlockchain.isEmpty()){
                des.openTransaction(this,amount);

            }
            //如果目标账户已开户，将receiveBlock添加到目标账户区块链中
            else {
                receiveBlock r=new receiveBlock();
                r.previous=des.ownBlockchain.getLast().hash;
                r.source=a.hash;
                r.work=amount;
                r.signature=des.signature;
                r.hash=r.calculateHash();
                des.ownBlockchain.add(r);
                des.balance+=amount;


            }




        }


    }




}
