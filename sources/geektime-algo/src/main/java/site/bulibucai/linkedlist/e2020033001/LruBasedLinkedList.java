package site.bulibucai.linkedlist.e2020033001;

import java.util.Scanner;

/**
 * @description: 使用带头单链表实现 LRU
 * @author: Ian Zheng
 * @date: 2020-03-30
 */
public class LruBasedLinkedList<T> {

  private final static Integer DEFAULT_CAPACITY = 10;

  /**
   * 链表容量
   */
  private Integer capacity;

  /**
   * 链表元素数量
   */
  private Integer count;

  /**
   * 链头
   */
  private DataNode header;

  public LruBasedLinkedList(Integer capacity) {
    this.capacity = capacity;
    this.count = 0;
    this.header = new DataNode(null, null);
  }

  public LruBasedLinkedList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * LRU 机制的实现。逻辑如下。
   *
   * <p>如果存在前驱结点，那么删除 data 结点，并在 index=0 处插入</p>
   * <p>如果不存在前驱结点且链表有空闲空间，那么直接在 index=0 插入</p>
   * <p>如果不存在前驱结点且链表没有空闲空间，那么删除尾结点，且在 index=0 插入</p>
   */
  public void lru(T data) {
    // 如果 data 存在，那么其前驱结点必定存在（带头链表）
    DataNode prevNode = findPrevNode(data);

    if (prevNode != null) {
      removeNodeAfterPrevNode(prevNode);
      insertAtIndex0(data);
    } else {
      if (count >= capacity) {
        // 移除尾结点
        removeLastNode();
      }
      insertAtIndex0(data);
    }
  }

  /**
   * 查找 data 的前驱结点
   *
   * <p>空链表，或链表中不存在 data 时，返回值为 <code>null</code></p>
   */
  private DataNode findPrevNode(T data) {
    DataNode prevNode = header;
    while (prevNode.getNext() != null) {
      if (prevNode.getNext().getData().equals(data)) {
        return prevNode;
      }
      prevNode = prevNode.getNext();
    }
    return null;
  }

  /**
   * 移除前驱结点的下一个结点
   */
  private void removeNodeAfterPrevNode(DataNode prevNode) {
    prevNode.setNext(prevNode.getNext().getNext());
    count--;
  }

  /**
   * 在 index=0 处插入结点
   */
  private void insertAtIndex0(T data) {
    header.setNext(new DataNode(data, header.getNext()));
    count++;
  }

  /**
   * 移除尾结点
   *
   * <p>重点在于如何找到尾结点的前驱结点</p>
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
   * 打印
   */
  private void print() {
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

    while (true) {
      int temp = scanner.nextInt();
      if (temp == 1000) {
        break;
      }
      list.lru(temp);
      list.print();
    }
  }
}
