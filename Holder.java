// Holderクラス
public class Holder {
    // protectedは、子クラスからアクセス可能。
    protected int    number;
    protected String name;

    // コンストラクタ
    public Holder(int number, String name) {
        this.number = number;
        this.name   = name;
    }

    // System.out.println(holder)を呼び出すと、
    // 以下のように表示されるので、デバッグ等に便利。
    // Holder{number=1, name='Ayako'}
    @Override
    public String toString() {
        return String.format("Holder{number=%d, name='%s'}", this.number, this.name);
    }
}
