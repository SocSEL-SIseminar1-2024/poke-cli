package cli.commands.help;

public class HelpCommand {
    public void run() {

        // テキストに使う色の宣言
		final String RED = "\u001b[00;31m";
		final String YELLOW = "\u001b[00;33m";
		final String BLACK = "\u001b[00;30m";

        //出力
        System.out.println(RED + "poke get 数字");
        System.out.println(BLACK + "　ポケモンの名前を数字分リスト表示します。");
        System.out.println(RED + "poke status ポケモン名");
        System.out.println(BLACK + "　指定されたポケモンの種族値を表示します。");
      }
}
