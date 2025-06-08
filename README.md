# ジェネリクス理解のために
Javaにおけるジェネリクスとは何でしょうか？


## 静的型付け言語と動的型付け言語
静的型付け言語と動的型付け言語の違いは、型の定義方法と型の推論方法の違いです。以下に具体例を示します。

## 静的型付け言語において、型宣言が必要な理由
CやJavaのような言語で変数に型宣言が必要なのは、主に以下の理由からです。

1. メモリの効率的な管理
C言語やJavaなどの静的型付け言語では、変数宣言時に型を指定します。これにより、コンパイラは必要なメモリ量を事前に把握し、効率的にメモリを確保します。例えば、int型は4バイト、double型は8バイトのメモリを必要とします。この仕組みにより、実行時のパフォーマンスが向上します。

2. コンパイル時のエラーチェック
型宣言により、コンパイラはコードの型の整合性をチェックできます。例えば、文字列を期待する関数に数値を渡すと、コンパイル時にエラーを検出できます。これにより、実行時エラーを未然に防ぎます。

3. まとめ
CやJavaのような静的型付け言語では、変数自体が型を持っています。つまり、「この変数は整数を入れます」とあらかじめ宣言します。

## 動的型付け言語におけて、型宣言が不要な理由
RubyやPythonのような動的型付け言語の場合、変数の型宣言は不要です。
これは、変数の型が実行時に割り当てられる値によって動的に決定されるためです。

```Ruby
n = 10 # nはFixnum（整数）型になる
n = "hello" # nはString（文字列）型になる
```

RubyやPythonでは値（オブジェクト）が型を持っています。変数そのものは特定の型に縛られず、様々な型のオブジェクトを参照できます。

Rubyでは、すべての値（10や"hello"など）はオブジェクトであり、それぞれのオブジェクトは特定のクラス（FixnumやStringなど）に属しています。変数は単にこれらのオブジェクトを参照するラベルにすぎません。

n = 10と書くと、変数nはFixnumオブジェクト10を参照します。その後でn = "hello"とすると、nは新たにStringオブジェクト"hello"を参照するようになります。変数自体に型があるのではなく、変数が参照するオブジェクトが型（クラス）を持っているのです。

Rubyのインタプリタは、コードを実行する際に各リテラルの型を認識し、適切なクラスのオブジェクトとしてメモリ上に生成します。この型の解決は実行時に行われるため、同じ変数に異なる型のオブジェクトを代入することができます。

## 静的型付け言語における型の効用
同じビット列を異なる意味に解釈するのは、型のまさに主要な効用の一つです。
(舞台の役者がキャスティングされることにより、違う役割を演じることができるのと同様です)

Javaのような静的型付け言語において、型は以下の重要な役割を果たします。

1. ビット列の「意味」を定義する
コンピュータの内部では、すべてのデータは究極的にはビット列（0と1の並び）として表現されます。しかし、このビット列が何を意味するのかは、そのビット列がどのような型として解釈されるかによって全く異なります。

0b01000001というビット列は、2進数で65という数値です。

char c = 0b01000001; の場合: char型は文字を表すために使われ、通常はUnicodeの文字コードを格納します。この場合、65という数値は、Unicode（より具体的にはASCII互換の部分）における文字コードU+0041として解釈されます。U+0041は**'A'**という文字に対応します。
int n = 0b01000001; の場合: int型は整数値を表すために使われます。この場合、65という数値は、そのまま整数値の65として解釈されます。
このように、同じ0b01000001というビット列でも、char型として扱うかint型として扱うかによって、そのビット列が表す「意味」が全く変わるのです。型は、このビット列の解釈方法（コンテキスト）を明確に定義する役割を担っています。

2. オペレーションの妥当性を保証する

型は、そのデータに対してどのような操作（演算やメソッド呼び出し）が許されるかをコンパイラに伝えます。

char c;の場合、cに対しては文字に関する操作（例: Character.isUpperCase(c)）が可能です。
int n;の場合、nに対しては数値に関する操作（例: n + 10、n * 2）が可能です。
もし型がなければ、コンパイラは 0b01000001 + 10 という操作が、「'A'と10を足す」という無意味な操作なのか、「65と10を足す」という意味のある操作なのかを判断できません。型があることで、コンパイラは開発者の意図を理解し、誤った操作をコンパイル時に検出し、エラーとして報告することができます。

3. メモリの管理

型はコンパイラが変数に割り当てるべきメモリのサイズと形式を決定します。
charは通常2バイト（16ビット）でUnicode文字を格納します。
intは通常4バイト（32ビット）で整数を格納します。
たとえ初期値のビット列が同じように見えても、型によって確保されるメモリサイズや、そのメモリのどの部分が「値」として解釈されるかが異なります。

4. まとめ

同じビット列を異なる意味に解釈させる能力は、型の最も根本的な効用です。型があることで、プログラマはコンピュータの低レベルなビット表現を意識することなく、より高レベルな「文字」や「数値」といった抽象概念でプログラムを記述できます。コンパイラは、型情報に基づいて、その抽象概念をビット列の操作へと正確に変換してくれるのです。

## Javaにおけるジェネリクスとは

ジェネリクスは、型を引数として受け取る能力を提供する機能です。具体例をあげます。

```java
// 1. int型の変数の例
int num1 = 10;
int num2 = 20;
int num3 = 30;
```

複数の類似したデータを一元的に管理する際には、配列を使用するととても便利です。

```java
// 2. int型の配列の例
int[] numbers = new int[3];   // サイズ3の配列
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
```

Javaにおける「配列 (Array)」と「ArrayList」は、どちらも複数の要素を格納するデータ構造です。
しかし、配列は固定サイズのデータ構造であり、要素の追加や削除はできません。ArrayListは動的配列であり、要素の追加や削除が可能です。また、配列はlength以外のメソッドを有していませんが、ArrayListはadd, remove, size, getなど様々なメソッドを提供します。

```java
// 3. ArrayList<Integer>の例
import java.util.ArrayList;

ArrayList<Integer> numberList = new ArrayList<>();
numberList.add(10);
numberList.add(20);
numberList.add(30);
```

ArrayList<Integer>の<Integer>が型パラメータで、「このArrayListにはInteger型の要素だけを格納する」と宣言しています。これにより、コンパイラはnumberListがIntegerオブジェクトのみを扱うことを認識します。

int型の配列やString型の配列を宣言する際は、new int[]や new String[]のように型を明示することで、コンパイラは配列の要素の型を認識することができました。しかしながら、new ArrayList<>のように型を明示しないと、コンパイラはArrayListの要素の型を認識することができません。コンパイラにArrayListの要素の型を認識させるために導入されたのが、ジェネリクスです。これにより、コンパイル時に型チェックが行われ、型安全性が向上します。

## ジェネリクスがない時代の課題点
ジェネリクスが導入される以前のJava（JDK 1.4以前）では、ArrayListなどのコレクションは、あらゆる型のオブジェクトを格納できるObject型としてデータを扱っていました。

これには2つの大きな課題がありました。
 * 意図しない型のデータが混入する危険性
   String型のリストを意図していても、誤ってInteger型などを追加できてしまい、コンパイル時にはエラーになりません。

```java
// ジェネリクスがない場合
List list = new ArrayList();
list.add("Hello");
list.add(123); // 本来は入れたくないが、コンパイルエラーにならない
```

 * 取り出す際にキャストが必須で、実行時エラーの危険性
   リストからデータを取り出す際、それがどの型であるかをプログラマが覚えておき、明示的にキャスト（型変換）する必要がありました。もし型を間違えると、実行時にClassCastExceptionというエラーが発生します。

```java
// 実行時エラー(ClassCastException)が発生する
String text = (String) list.get(1);
```

## ジェネリクスによる解決
ジェネリクスを使うと、コレクションが扱うデータ型を<>（山括弧）で明確に指定できます。

```java
// ジェネリクスを使用した場合
List<String> list = new ArrayList<>();
list.add("Hello");

// list.add(123); // この行はコンパイルエラーになる！

String text = list.get(0); // キャストが不要
```

これにより、以下のメリットが生まれます。
 * 型安全性 (Type Safety)
   指定した型以外のデータを追加しようとすると、コンパイルの段階でエラーを検出できます。実行時エラーのリスクを大幅に減らせます。
 * キャストが不要
   コンパイラが型を記憶しているため、データを取り出す際にキャストが不要になります。コードがシンプルになり、可読性が向上します。
 * コードの再利用性 (Code Reusability)
   List<String>やList<Integer>のように、同じクラスのロジックを様々な型で安全に再利用できます。

## Holderクラス / SilverHolderクラス の例

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

```java
// SilverHolderクラスは、Holderクラスを継承する。
public class SilverHolder extends Holder {
    public SilverHolder(int number, String name) {
        // 親クラスのコンストラクタを呼び出す。
        super(number, name);
    }

    @Override
    public String toString() {
        return String.format("SilverHolder: %d %s", this.number, this.name);
    }
}
```

```java
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
```

## 不変性 (Invariance) / 共変性 (Covariance) / 反変性 (Contravariance)

さて、ジェネリクススの基本的な使い方が理解できたところで、ジェネリクススの不変性 / 共変性 / 反変性について学んでいきましょう。

### 不変性 (Invariance) とは
「不変（invariant）」は、「変化する（variant）」の否定を意味します。つまり、SilverHolder が Holder のサブタイプだとしても、List<SilverHolder> と List<Holder> の間に自動的な型階層（サブタイプ関係）が生成されない、つまり「型引数のサブタイプ関係に影響されない」という点に着目しています。ジェネリクス型そのものの型関係が、型引数の変化によって「変動しない」という意味合いです。なので、この性質を「不変性」と呼びます。これにより、**型安全性を保証**します。

```java
// 不変性の例
ArrayList<Holder> holderList = new ArrayList<SilverHolder>(); // コンパイルエラー
```

このsilverHolderListは、見た目上はHolder型の要素を格納できるリストとして扱われます。
異なるサブクラスを追加することを考えてみましょう。ここで、Holderクラスの別のサブクラスであるGoldHolderが存在し、GoldHolderとSilverHolderの間に継承関係がないとします。

```java
public class GoldHolder extends Holder {
    // ...
}
GoldHolder queen = new GoldHolder(4, "Queen");
```

silverHolderListはArrayList<Holder>型なので、Holderのインスタンス、またはそのサブクラスのインスタンスを追加できるはずです。

```java
silverHolderList.add(queen); // コンパイルOK (Holder型のリストだから)
```

このとき、silverHolderListが実際に参照しているのはArrayList<SilverHolder>のインスタンスです。あなたはGoldHolder型のqueenオブジェクトを、SilverHolderしか格納できないはずのArrayList<SilverHolder>に追加しようとしています。

もしこれが許されると、silverHolderListにGoldHolderが追加されてしまい、その後、このリストからSilverHolderを取り出して使おうとした場合に、ClassCastExceptionのような実行時エラーが発生する可能性があります。

```Java
SilverHolder retrievedSilver = silverHolderList.get(0); // もしqueenが入っていたらClassCastException！
```

Javaのジェネリクスは、このようなコンパイル時には見つけられない実行時エラーを防ぐことを最優先しているため、上記のようなArrayList<Holder> silverHolderList = new ArrayList<SilverHolder>(); の代入をコンパイル段階で禁止し、型安全性を保証しているのです。

### 共変性 (Covariance) とは
「共変（covariant）」という言葉は、「共に（co-）」、「変化する（variant）」という組み合わせから来ています。つまり、元の型の変化（サブタイプ化）と同じ方向（同じ意味での「より特殊な型」への変化）に、コンテナ型（または関数型）の型も変化するという意味合いです。

* 共変性 (List<? extends Holder>): 読み取り専用の目的で、より柔軟な型を許容します。
```java
ArrayList<SilverHolder> silverList = new ArrayList<>();
silverList.add(ichiro);

// List<? extends Holder> は、Holder またはそのサブクラスのリストを受け入れます。
// このリストからはHolderとして要素を読み取れますが、（null以外の）書き込み（追加）はできません。
ArrayList<? extends Holder> anyHolderList = silverList; // OK
Holder h = anyHolderList.get(0); // OK
// anyHolderList.add(new Holder(4, "New")); // コンパイルエラー！
```

### 反変性 (Contravariance) とは
「反変（contravariant）」は、「反対（contra-）」、「変化する（variant）」という組み合わせから来ています。つまり、元の型の変化（サブタイプ化）の逆方向（同じ意味での「より一般的な型」への変化）に、コンテナ型（または関数型）の型も変化するという意味合いです。

* 反変性 (List<? super SilverHolder>): 書き込み可能な目的で、より柔軟な型を許容します。
```java
// List<? super SilverHolder> は、SilverHolder またはそのスーパークラスのリストを受け入れます。
// このリストにはSilverHolderやそのサブクラスを追加できますが、読み取りはObjectとしてしか保証されません。
ArrayList<? super SilverHolder> consumeSilverHolderList = holderList; // OK
consumeSilverHolderList.add(ichiro); // OK
consumeSilverHolderList.add(new SilverHolder(400, "Shiro")); // OK
Object obj = consumeSilverHolderList.get(0); // OK (Object型として取り出す)
// SilverHolder s = consumeSilverHolderList.get(0); // コンパイルエラー！
```
