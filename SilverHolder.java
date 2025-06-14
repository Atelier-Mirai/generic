// SilverHolderクラスは、Holderクラスを継承する。
public class SilverHolder extends Holder {
    public SilverHolder(int number, String name) {
        // 親クラスのコンストラクタを呼び出す。
        super(number, name);
    }

    @Override
    public String toString() {
        return String.format("SilverHolder{number=%d, name='%s'}", this.number, this.name);
    }
}
