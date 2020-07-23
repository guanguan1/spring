package com.guan.net.netty.demo;

/**
 * -----链表形式调用------netty就是类似的这种形式
 * @Author : guantenghua
 * @create 2020/5/31 11:48
 */
public class PipelineDemo {
    // 初始化的时候构造一个head，作为责任链的开始，但是并没有具体的处理
    public HandlerChainContext head = new HandlerChainContext(new AbstractHandler() {
        @Override
        void doHandler(HandlerChainContext handlerChainCntext, Object arg0) {
            handlerChainCntext.runNext(arg0);
        }
    });

    public void requestProcess(Object arg0){
        this.head.handler(arg0);
    }

    public void addLast(AbstractHandler handler){
        HandlerChainContext context = head;
        while (context.next != null){
            context = context.next;
        }
        context.next = new HandlerChainContext(handler);
    }

    public static void main(String[] args){
        PipelineDemo pipelineChainDemo = new PipelineDemo();
        pipelineChainDemo.addLast(new handlerTwo());
        pipelineChainDemo.addLast(new handlerOne());
        pipelineChainDemo.addLast(new handlerOne());
        pipelineChainDemo.addLast(new handlerTwo());
        pipelineChainDemo.requestProcess("火车呜呜呜~~~");
    }

}
// handler上下文，主要负责维护链，和链的执行
class HandlerChainContext {
    // 下一个节点
    HandlerChainContext next;
    AbstractHandler handler;

    public HandlerChainContext(AbstractHandler handler) {
        this.handler = handler;
    }

    void handler(Object arg0){
        this.handler.doHandler(this, arg0);
    }

    void runNext(Object arg0){
        if(this.next != null){
            this.next.handler(arg0);
        }
    }
}
// 处理器抽象类
abstract class AbstractHandler{
    // 处理器，制作一件事，再传入的字符串中增加个尾巴
    abstract void doHandler(HandlerChainContext handlerChainCntext, Object arg0);
}

class handlerOne extends AbstractHandler{

    @Override
    void doHandler(HandlerChainContext handlerChainCntext, Object arg0) {
        arg0 = arg0.toString() + "..handlerOne的小尾巴.....";
        System.out.println(arg0);

        handlerChainCntext.runNext(arg0);
    }
}

class handlerTwo extends AbstractHandler{

    @Override
    void doHandler(HandlerChainContext handlerChainCntext, Object arg0) {
        arg0 = arg0.toString() + "..handlerTwo的小尾巴.....";
        System.out.println(arg0);

        handlerChainCntext.runNext(arg0);
    }
}
