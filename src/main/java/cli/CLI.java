package cli;

import java.util.Scanner;
import cli.commands.get.GetPokeNameList;
import cli.commands.status.GetPokeStatus;
import cli.commands.hello.HelloCommand;

public class CLI implements Runnable {
  private String[] args;
  Scanner sc = new Scanner(System.in);

  CLI(String[] args) {
    this.args = args;
  }

  @Override
  public void run() {
    try {
      // コマンドを格納(poke get なら get)
      String command = args[0];

      // コマンドごとに処理を分岐
      if (command.equals("get")) {
        /* getコマンドが実行されると整数値を繰り返し入力できるようになる。
        入力値にfinishを入力すると繰り返し処理が終了する。*/
        while (true){
          System.out.print("PokeNumber: ");
          if (sc.hasNextInt()){
            String InputNum = sc.next();
            int GetPokeNum = Integer.parseInt(InputNum);
            if (GetPokeNum > 0 && GetPokeNum <= 1302){
              new GetPokeNameList(GetPokeNum).run();
            } else {
              System.out.println("Not exist");
            }
          } else {
            if (sc.hasNext("finish")){
              System.out.println("See you again!");
              break;
            } else {
              System.out.println("Input Error!");
              sc.next();
              continue;
            }
          }
        }
      } else if (command.equals("status")){
        while (true){
          /*  statusコマンドも同様に実行されると整数値を繰り返し入力できるようになる。
        入力値にfinishを入力すると繰り返し処理が終了する。*/
          System.out.print("PokeNumber: ");
          if (sc.hasNextInt()){
            String InputNum = sc.next();
            int GetPokeNum = Integer.parseInt(InputNum);
            if (GetPokeNum > 0 && GetPokeNum <= 1302){
              new GetPokeStatus(GetPokeNum).run();
            } else {
              System.out.println("Not exist");
            }
          } else {
            if (sc.hasNext("finish")){
              System.out.println("See you again!");
              break;
            } else {
              System.out.println("Input Error!");
              sc.next();
              continue;
            }
          }
        }
      } else if (command.equals("hello")) {
        new HelloCommand().run();
      }
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
