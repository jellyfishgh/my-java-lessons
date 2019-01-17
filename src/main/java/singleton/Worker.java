package singleton;

import java.util.ArrayList;

public class Worker implements Runnable {
  private ArrayList<Singleton> list;
  public Worker(ArrayList<Singleton> list) {
    this.list = list;
  }

  @Override
  public void run() {
    list.add(Singleton.getIntance());
  }

}
