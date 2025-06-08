import java.util.ArrayList;

public class GenericExample {
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

        System.out.println(ayako);  // Holder{number=1, name='Ayako'}
        System.out.println(kayoko); // Holder{number=2, name='Kayoko'}
        System.out.println(sayoko); // Holder{number=3, name='Sayoko'}
        System.out.println(ichiro); // SilverHolder{number=100, name='Ichiro'}
        System.out.println(jiro);   // SilverHolder{number=200, name='Jiro'}
        System.out.println(saburo); // SilverHolder{number=300, name='Saburo'}

        // 配列による例
        Holder[] holders = new Holder[6];
        holders[0] = ayako;
        holders[1] = kayoko;
        holders[2] = sayoko;
        holders[3] = ichiro;
        holders[4] = jiro;
        holders[5] = saburo;
        for (Holder h : holders) {
            System.out.println(h);
        }
        // Holder{number=1, name='Ayako'}, Holder{number=2, name='Kayoko'},
        // Holder{number=3, name='Sayoko'}, SilverHolder{number=100, name='Ichiro'},
        // SilverHolder{number=200, name='Jiro'}, SilverHolder{number=300,name='Saburo'}

        // ArrayList<Holder>は、Holder型とそのサブタイプを格納できる動的配列。
        ArrayList<Holder> holderList = new ArrayList<>();
        holderList.add(ayako);
        holderList.add(kayoko);
        holderList.add(sayoko);
        holderList.add((Holder)ichiro); // ichiroはSilverHolder。可読性のため、明示的にキャスト。
        holderList.add((Holder)jiro);   
        holderList.add((Holder)saburo);
        for (Holder h : holderList) {
            System.out.println(h);
        }
        // Holder{number=1, name='Ayako'}, Holder{number=2, name='Kayoko'},
        // Holder{number=3, name='Sayoko'}, SilverHolder{number=100, name='Ichiro'},
        // SilverHolder{number=200, name='Jiro'}, SilverHolder{number=300,name='Saburo'}

        // ArrayList<SilverHolder>は、SilverHolder型(とそのサブタイプ)を格納できる動的配列。
        ArrayList<SilverHolder> silverHolderList = new ArrayList<>();
        silverHolderList.add(ichiro);
        silverHolderList.add(jiro);
        silverHolderList.add(saburo);
        for (SilverHolder sh : silverHolderList) {
            System.out.println(sh);
        }
        // SilverHolder{number=100, name='Ichiro'},
        // SilverHolder{number=200, name='Jiro'},
        // SilverHolder{number=300, name='Saburo'}
    }
}
