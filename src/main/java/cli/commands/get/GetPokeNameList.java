package cli.commands.get;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeNameList implements Runnable {
  private Number limit;
  private int fromlimit;
  private int tolimit;

  public GetPokeNameList(Number limit) {
    this.limit = limit;
  }

  public GetPokeNameList(int fromlimit, int tolimit) {
    this.fromlimit = fromlimit;
    this.tolimit = tolimit;
  }

  public void run() {
    // ポケモンのデータを取得
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon?limit=" + limit);
    String res = fetcher.getResponse();
 
    // resからポケモンの名前を取り出す
    String[] pokemonNames = res.split("\"name\":\"");

    // ポケモンの名前を表示させる
    if(limit != null) {
      for (int i = 1; i < pokemonNames.length; i++) {
        String pokemonName = pokemonNames[i].split("\"")[0];
        Logger.success(pokemonName); 
        System.out.println();
      }
    }else{
      for (int i = fromlimit; i < tolimit + 1; i++) {
        String pokemonName = pokemonNames[i].split("\"")[0];
        Logger.success(pokemonName); 
        System.out.println();
      }
    }
  }
}
