public class ArrayCovarianceErrorExample {

    public static void main(String[] args) {

        // SilverHolder型の配列を初期化
        SilverHolder[] silverHolders = {
                new SilverHolder(100, "Ichiro"),
                new SilverHolder(200, "Jiro"),
                new SilverHolder(300, "Saburo")
        };

        System.out.println("--- 初期状態 ---");
        for (SilverHolder sh : silverHolders) {
            System.out.println(sh);
        }
        System.out.println();

        // ここがポイント：配列の共変性
        // SilverHolder[] は Holder[] のサブタイプとして扱えるため、コンパイルOK
        Holder[] holders = silverHolders; // コンパイルエラーにならない！

        System.out.println("--- Holder[] 変数に代入後 ---");
        System.out.println("holders[0] の内容は: " + holders[0]); // SilverHolder(id=100, name='Ichiro')
        System.out.println();

        // 別の Holder サブクラスのインスタンスを生成
        GoldHolder queen = new GoldHolder(400, "Queen");

        System.out.println("--- 問題の発生 ---");
        try {
            // holders 変数は Holder[] 型なので、コンパイル時には GoldHolder の代入が許可される
            // しかし、実際に参照している配列の実体は SilverHolder[] であるため、
            // ここで ArrayStoreException が発生する！
            holders[1] = queen; // 実行時に ArrayStoreException が発生
            System.out.println("この行は実行されません。"); // エラーでスキップされる
        } catch (ArrayStoreException e) {
            System.err.println("!!! ArrayStoreException が発生しました !!!");
            System.err.println("エラーメッセージ: " + e.getMessage());
            System.err.println("原因: SilverHolder[] 配列に GoldHolder を代入しようとしました。");
        }

        System.out.println("\n--- 最終状態 ---");
        // 配列の元の要素を確認（エラーで代入が失敗しているため、元のJiroのまま）
        for (SilverHolder sh : silverHolders) { // 元の SilverHolder[] は変更されない
            System.out.println(sh);
        }
        // holders 変数を通じてアクセスした場合も同様
        for (Holder h : holders) {
            System.out.println(h);
        }
    }
}