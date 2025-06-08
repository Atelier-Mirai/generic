# ジェネリックについて

## 型とは何か

## Javaにおけるジェネリックとは

## コード例

以下にジェネリック説明用のコードを掲載する。

```java
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
    // Holder: 1 Ayako
    @Override
    public String toString() {
        return String.format("Holder: %d %s", this.number, this.name);
    }
}

```



