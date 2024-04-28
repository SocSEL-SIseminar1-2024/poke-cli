package cli.commands.status;

import cli.commands.get.GetPokeNameList;
import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeStatus implements Runnable {
  private String name;
  private Number num;

  public GetPokeStatus(String InputStr) {
    // runメソッド内で扱う変数を選択する
    if (isInteger(InputStr)){
      this.name = null;
      this.num = Integer.parseInt(InputStr);
    } else {
      this.name = InputStr;
      this.num = null;
    }
  }

  @Override
  public void run() {
    try{
      // ポケモンのデータを取得する
      // 参照: https://pokeapi.co/docs/v2#pokemon
      HttpRequest fetcher;
      if (num != null){
        fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + num);
      } else if (name != null){
        fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + name);
      } else {
        fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + name);
      }
      String res = fetcher.getResponse();

      // resからstatsを抜き出す
      String[] stats = res.split("\"stats\":\\[")[1].split("\\]")[0].split("\\},\\{");

      /* 種族値を表示させる。
      ポケモンの名前も出力される。*/ 
      if (num != null){
        Logger.attention("Pokemon status for ");
        new GetPokeNameList(num).run();
      } else if (name != null){
        Logger.attention("Pokemon status for " + name);
      } else {
        Logger.attention("Pokemon status for " + name);
      }
      System.out.println();
      
      for (String stat: stats) {
        String statName = stat.split("\"name\":\"")[1].split("\"")[0];
        int baseStat = Integer.parseInt(stat.split("\"base_stat\":")[1].split(",")[0]);
        Logger.log(statName + ": ");
        Logger.success(Integer.toString(baseStat));
        System.out.println();
      }
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.println("Not exist");
    }
  }

  private boolean isInteger(String InputStr) {
    // 入力された値が整数であるかを判定
    boolean judge = true;
    for(int i = 0; i < InputStr.length(); i++) {
      if(Character.isDigit(InputStr.charAt(i))) {
          continue;
      } else {
        judge = false;
        break;
      }
    }
    return judge;
  }
}
