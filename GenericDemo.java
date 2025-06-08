import java.util.ArrayList;

public class GenericDemo {
    public static void main(String[] args) {
        // 各クラスのインスタンスを生成する。
        // Holderは女性
        Holder       ayako  = new Holder(1, "Ayako");
        Holder       kayoko = new Holder(2, "Kayoko");
        Holder       sayoko = new Holder(3, "Sayoko");
        // SilverHolderは男性（女性から生み出される子クラス）
        SilverHolder ichiro = new SilverHolder(100, "Ichiro");
        SilverHolder jiro   = new SilverHolder(200, "Jiro");
        SilverHolder saburo = new SilverHolder(300, "Saburo");

        System.out.println(ayako);  // Holder: 1 Ayako
        System.out.println(kayoko); // Holder: 2 Kayoko
        System.out.println(sayoko); // Holder: 3 Sayoko
        System.out.println(ichiro); // SilverHolder: 100 Ichiro
        System.out.println(jiro);   // SilverHolder: 200 Jiro
        System.out.println(saburo); // SilverHolder: 300 Saburo

        // 配列による例
        Holder[] holders = new Holder[6];
        holders[0] = ayako;
        holders[1] = kayoko;
        holders[2] = sayoko;
        holders[3] = ichiro;
        holders[4] = jiro;
        holders[5] = saburo;
        System.out.println(holders);
        // [Holder: 1 Ayako, Holder: 2 Kayoko, Holder: 3 Sayoko, SilverHolder: 100 Ichiro, SilverHolder: 200 Jiro, SilverHolder: 300 Saburo]

        // ArrayList<Holder>は、Holder型とそのサブタイプを格納できる動的配列。
        ArrayList<Holder> holderList = new ArrayList<>();
        holderList.add(ayako);
        holderList.add(kayoko);
        holderList.add(sayoko);
        holderList.add((Holder)ichiro); // ichiroはSilverHolder。可読性のため、明示的にキャスト。
        holderList.add((Holder)jiro);   
        holderList.add((Holder)saburo);
        System.out.println(holderList);
        // [Holder: 1 Ayako, Holder: 2 Kayoko, Holder: 3 Sayoko, SilverHolder: 100 Ichiro, SilverHolder: 200 Jiro, SilverHolder: 300 Saburo]

        // ArrayList<SilverHolder>は、SilverHolder型(とそのサブタイプ)を格納できる動的配列。
        ArrayList<SilverHolder> silverHolderList = new ArrayList<>();
        silverHolderList.add(ichiro);
        silverHolderList.add(jiro);
        silverHolderList.add(saburo);
        System.out.println(silverHolderList);
        // [SilverHolder: 100 Ichiro, SilverHolder: 200 Jiro, SilverHolder: 300 Saburo]
    }
}
