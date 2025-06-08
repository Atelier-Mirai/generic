// SilverHolderクラスは、Holderクラスを継承する。
public class GoldHolder extends Holder {
    public GoldHolder(int number, String name) {
        // 親クラスのコンストラクタを呼び出す。
        super(number, name);
    }

    @Override
    public String toString() {
        return String.format("GoldHolder{number=%d, name='%s'}", this.number, this.name);
    }
}
