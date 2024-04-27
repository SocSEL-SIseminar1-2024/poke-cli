package cli.commands.status;

import cli.commands.get.GetPokeNameList;
import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeStatus implements Runnable {
  private Number num;

  public GetPokeStatus(Number num) {
    this.num = num;
  }

  @Override
  public void run() {
    // ポケモンのデータを取得する
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + num);
    String res = fetcher.getResponse();

    // resからstatsを抜き出す
    String[] stats = res.split("\"stats\":\\[")[1].split("\\]")[0].split("\\},\\{");

    /* 種族値を表示させる。
    数値が入力されるとポケモンの名前も出力される。*/ 
    Logger.attention("Pokemon status for ");
    new GetPokeNameList(num).run();
    System.out.println();
    
    for (String stat: stats) {
      String statName = stat.split("\"name\":\"")[1].split("\"")[0];
      int baseStat = Integer.parseInt(stat.split("\"base_stat\":")[1].split(",")[0]);
      Logger.log(statName + ": ");
      Logger.success(Integer.toString(baseStat));
      System.out.println();
    }
  }
}
