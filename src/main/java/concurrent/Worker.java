package concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

public class Worker implements Runnable {
  private ConcurrentSkipListMap<String, Integer> map;
  private String key;
  private int value;
  private boolean flag;

  public Worker(ConcurrentSkipListMap<String, Integer> map, String key, int value, boolean flag) {
    this.map = map;
    this.key = key;
    this.value = value;
    this.flag = flag;
  }

  @Override
  public void run() {
    if (flag)
      map.put(key, value);
    else
      map.remove(key);
  }
}
