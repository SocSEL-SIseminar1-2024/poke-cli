package cli.commands.name;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeName {
    private Number number;

  public GetPokeName(Number number) {
    this.number = number;
  }

  public void run() {
    // ポケモンのデータを取得
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon?limit=" + number);
    String res = fetcher.getResponse();
 
    // resからポケモンの名前を取り出す
    String[] pokemonNames = res.split("\"name\":\"");

    // ポケモンの名前を表示させる
    String pokemonName = pokemonNames[pokemonNames.length - 1].split("\"")[0];
    Logger.success(pokemonName); 
    System.out.println();
  }
}
