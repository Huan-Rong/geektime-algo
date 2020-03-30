package site.bulibucai.linkedlist.e2020033001;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-03-30
 */
public class DataNode<T> {

  private T data;
  private DataNode next;

  public DataNode(T data, DataNode next) {
    this.data = data;
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public DataNode getNext() {
    return next;
  }

  public void setNext(DataNode next) {
    this.next = next;
  }
}
