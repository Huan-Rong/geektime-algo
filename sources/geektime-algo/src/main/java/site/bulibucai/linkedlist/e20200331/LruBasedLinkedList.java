package site.bulibucai.linkedlist.e20200331;

import java.util.Scanner;
import javafx.scene.chart.PieChart.Data;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-03-31
 */
public class LruBasedLinkedList<T> {

  private final static Integer DEFAULT_CAPACITY = 10;

  private Integer capacity;

  private Integer count;

  private DataNode<T> header;

  public LruBasedLinkedList(Integer capacity) {
    this.capacity = capacity;
    this.count = 0;
    this.header = new DataNode<T>();
  }

  public LruBasedLinkedList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * 实现 LRU 功能机制
   */
  public void lru(T data) {
    DataNode dataNode = findPrevNode(data);

    if (dataNode == null) {
      if (count >= capacity) {
        removeLastNode();
      }
      insertAtIndex0(data);
    } else {
      removeNodeAfterPrev(dataNode);
      insertAtIndex0(data);
    }
  }

  /**
   * 查找指定 data 的前驱结点
   */
  private DataNode findPrevNode(T data) {
    // 这里使用了链头，那么链表的第一个结点和其他节点是否存在的判断，就能够使用相同的一段代码来处理。
    DataNode dataNode = header;
    while (dataNode.getNext() != null) {
      if (dataNode.getNext().getData().equals(data)) {
        return dataNode;
      }
      dataNode = dataNode.getNext();
    }
    return null;
  }

  /**
   * 移除前驱结点的下一个结点
   */
  private void removeNodeAfterPrev(DataNode prevNode) {
    prevNode.setNext(prevNode.getNext().getNext());
    count--;
  }

  /**
   * 在 Index=0 处插入结点
   */
  private void insertAtIndex0(T data) {
    header.setNext(new DataNode(data, header.getNext()));
    count++;
  }

  /**
   * 移除尾结点
   */
  private void removeLastNode() {
    DataNode dataNode = header;
    if (dataNode.getNext() == null) {
      return;
    }

    while (dataNode.getNext().getNext() != null) {
      dataNode = dataNode.getNext();
    }
    dataNode.setNext(null);
    count--;
  }

  /**
   * 打印链表
   */
  private void printAll() {
    DataNode dataNode = header;
    while (dataNode.getNext() != null) {
      System.out.print(String.format("%d ", dataNode.getNext().getData()));
      dataNode = dataNode.getNext();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    LruBasedLinkedList list = new LruBasedLinkedList();
    Scanner scanner = new Scanner(System.in);

    while(true) {
      int temp = scanner.nextInt();
      if (temp == 1000) {
        break;
      }
      list.lru(temp);
      list.printAll();
    }
  }
}
