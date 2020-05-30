package Nano;
abstract class Block {
    public String hash;

    static enum type{
        open, send, receive, change
    }
    int work;
    type own;
    String signature;


}
class openBlock extends Block{
    String address;
    String representative;

    String calculateHash(){
        String s=StringUtil.applySha256(this.address+this.representative
                +Integer.toString(work) +this.own.toString()+this.signature);
        return s;

    }
    public String hash;
    openBlock(){
        own=type.open;

    }

}

class sendBlock extends Block{
    String previous;
    int balance;
    String destination;
    final type own=type.send;
    String calculateHash(){
        String s=StringUtil.applySha256(this.previous+Integer.toString(balance)
                +this.destination
                +Integer.toString(work) +this.own.toString()+this.signature);
        return s;
    }
    public String hash;
}
class receiveBlock extends Block{
    String previous;
    String source;
    final type own=type.receive;
    String calculateHash(){
        String s=StringUtil.applySha256(this.previous+this.source
                +Integer.toString(work) +this.own.toString()+this.signature);
        return s;
    }
    public String hash;

}
class changeBlock extends Block{
    String previous;
    String representative;
    final type own=type.change;
    String calculateHash(){
        String s=StringUtil.applySha256(this.previous+this.representative
                +Integer.toString(work) +this.own.toString()+this.signature);
        return s;
    }
    public String hash;

}
public class blockdemo {
}
