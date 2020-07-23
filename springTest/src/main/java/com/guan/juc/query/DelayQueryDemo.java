package com.guan.juc.query;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * (基于PriorityQueue来实现的)是一个存放Delayed元素的无界阻塞队列
 * 只有在延迟期满时才能从中提取元素。
 * 该队列的头部是延迟期满后保存时间最长的Delayed元素。
 * 当一个元素的getDelay(TimeUnit.NANOSECONDS)方法返回一个小于或等于零的值时，
 * 则出现期满，poll就以移除这个元素了。此队列不允许使用null元素。
 * @Author : guantenghua
 * @create 2020/6/12 11:35
 */
public class DelayQueryDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> delayQueue = new DelayQueue<Message>();
        // 这条消息5秒后发送
        Message message = new Message("message - 00001", new Date(System.currentTimeMillis() + 5000L));
        delayQueue.add(message);
        while(true){
            System.out.println(delayQueue.poll());
            Thread.sleep(1000);
        }
    }

}
class Message implements Delayed{

    /**
     * Returns the remaining delay associated with this object, in the
     * given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // 默认纳秒
        long duration = sendTime.getTime() - System.currentTimeMillis();
        return TimeUnit.NANOSECONDS.convert(duration, TimeUnit.MICROSECONDS);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Delayed o) {
        return o.getDelay(TimeUnit.NANOSECONDS) > this.getDelay(TimeUnit.NANOSECONDS) ? 1 : -1;
    }

    String content;
    Date sendTime;

    /**
     * @param content  消息内容
     * @param sendTime 定时发送
     */
    public Message(String content, Date sendTime) {
        this.content = content;
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
