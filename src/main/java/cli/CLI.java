package cli;

import java.util.Scanner;
import cli.commands.get.GetPokeNameList;
// import cli.commands.status.GetPokeStatus;
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
        0以下か1302以降の整数か整数でない値が入力されるとコマンドの実行が終了する。*/
        while (true){
          System.out.print("PokeNumber: ");
          if (sc.hasNextInt()){
            int GetPokeNum = sc.nextInt();
            if (GetPokeNum > 0 && GetPokeNum <= 1302){
              new GetPokeNameList(GetPokeNum).run();
            } else {
              System.out.println("get command finish");
              break;
            }
          } else {
            System.out.println("Input Error!");
            break;
          }
        }
      }
      
      // if (option != null && command.equals("status")) {
      //   String name = option;
      //   new GetPokeStatus(name).run();
      // }
      
      if (command.equals("hello")) {
        new HelloCommand().run();
      }
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
