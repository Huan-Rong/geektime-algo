package site.bulibucai.linkedlist.e20200330;


import java.util.Scanner;

/**
 * @description: 使用带头单链表实现
 * @author: Ian Zheng
 * @date: 2020-03-30
 */
public class LruBasedLinkedList<T> {

  /**
   * 默认链表容量
   */
  private final static Integer DEFAULT_CAPACITY = 10;

  /**
   * 记录 LinkedList 的元素个数
   */
  private Integer count;

  /**
   * 记录 LinkedList 的容量
   */
  private Integer capacity;

  /**
   * 链头
   */
  private DataNode<T> header;

  public LruBasedLinkedList() {
    this(DEFAULT_CAPACITY);
  }

  public LruBasedLinkedList(Integer capacity) {
    this.capacity = capacity;
    this.count = 0;
    this.header = new DataNode<T>();
  }

  /**
   * 实现 LRU 缓存淘汰机制
   * @param data
   */
  public void lru(T data) {
    // 如果缓存中存在 data，那么一定存在其前驱结点(带头链表的实现)
    DataNode prevNode = getPrevNode(data);

    if (prevNode != null) {
      removeNodeAfterPrev(prevNode);
      insertDataAt0(data);
    } else {
      if (count >= capacity) {
        removeNodeAtEnd();
      }
      insertDataAt0(data);
    }
  }

  /**
   * 在 index=0 处插入新结点
   * @param data
   */
  private void insertDataAt0(T data) {
    DataNode<T> dataNode = new DataNode<T>(data, header.getNext());
    header.setNext(dataNode);
    count++;
  }

  /**
   * 获取元素的前一个结点
   * @param data
   * @return
   */
  private DataNode getPrevNode(T data) {
    DataNode prev = header;
    while (prev.getNext() != null) {
      if (prev.getNext().getData().equals(data)) {
        return prev;
      }
      prev = prev.getNext();
    }
    return null;
  }

  private void removeNodeAfterPrev(DataNode prev) {
    prev.setNext(prev.getNext().getNext());
    count--;
  }

  /**
   * 删除尾结点
   */
  private void removeNodeAtEnd() {
    DataNode dataNode = header;
    // 空链表的处理
    if (dataNode.getNext() == null) {
      return;
    }

    // 用语义来理解 while 循环中的条件：判断下个结点是否为尾结点
    while (dataNode.getNext().getNext() != null) {
      dataNode = dataNode.getNext();
    }
    dataNode.setNext(null);
    count--;
  }

  /**
   * 打印
   */
  private void printAll() {
    DataNode dataNode = header;
    while(dataNode.getNext() != null) {
      dataNode = dataNode.getNext();
      System.out.print(dataNode.getData() + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    LruBasedLinkedList list = new LruBasedLinkedList();
    Scanner scanner = new Scanner(System.in);
    while(true) {
      int temp = scanner.nextInt();
      if (temp == 100) {
        break;
      }
      list.lru(temp);
      list.printAll();
    }
  }
}
