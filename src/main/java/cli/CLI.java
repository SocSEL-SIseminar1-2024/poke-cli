package cli;

import java.util.Scanner;
import cli.commands.get.GetPokeNameList;
import cli.commands.status.GetPokeStatus;
import cli.commands.hello.HelloCommand;

public class CLI implements Runnable {
  private String[] args;

  CLI(String[] args) {
    this.args = args;
  }

  @Override
  public void run() {
    try {
      // コマンドを格納(poke get なら get)
      String command = args[0];

      // コマンドごとに処理を分岐
      if (command.equals("hello")) {
        new HelloCommand().run();
      } else {
        InputProcess(command);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void InputProcess(String command){
    String InputStr = "";
    Scanner sc = new Scanner(System.in);
    /* getコマンドかstatusコマンドが実行されると整数値を繰り返し入力できるようになる。
    statusコマンドの場合はポケモンの名前も入力できる。
    入力値にfinishを入力すると繰り返し処理が終了する。*/
    while (true){
      if (command.equals("get")){
        System.out.print("PokeNumber: ");
      } else {
        System.out.print("PokeNumber or PokeName: ");
      }

      InputStr = sc.next();
      if (isInteger(InputStr)){
        int GetPokeNum = Integer.parseInt(InputStr);
        // 入力された数値と合致するポケモンが存在するかを判定
        if (GetPokeNum > 0 && GetPokeNum <= 1302){
          if (command.equals("get")){
            new GetPokeNameList(GetPokeNum).run();
          } else {
            new GetPokeStatus(InputStr).run();
          }
        } else {
          System.out.println("Not exist");
        }
      } else {
        // 文字列や小数など整数以外の値が入力された場合の処理
        if (InputStr.equals("finish")){
          System.out.println("See you again!");
          break;
        } else {
          if (command.equals("status")){
            try {
              new GetPokeStatus(InputStr).run();
            } catch (NumberFormatException e){
              System.out.println("Not exist");
            }
          } else {
            System.out.println("Input Error!");
            continue;
          }
        }
      }
    }
    sc.close();
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
