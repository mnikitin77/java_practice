package com.mvnikitin.practice.lesson3;

public class PingPongGame {
    private final Ball ball = new Ball();
    private final static int TURNS_COUNT = 10;
    private boolean isPingTurn = true;


    public void play() throws InterruptedException {

        Player ping = new Player("ping", true);
        Player pong = new Player("pong", false);

        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);

        pingThread.start();
        pongThread.start();
        pingThread.join();
        pongThread.join();

        System.out.println("Game over, man...");
    }

    public static void main(String[] args) throws InterruptedException {
        new PingPongGame().play();
    }

    static class Ball {
        public void hit(String hitMessage, int hitNumber) {
            System.out.println(Thread.currentThread().getName() + ": " + hitMessage + " #" + hitNumber);
        }
    }

    class Player implements Runnable {

        private final String name;
        private final boolean amIPingOrPong;

        public Player(String name, boolean isPing) {
            this.name = name;
            amIPingOrPong = isPing;
        }

        @Override
        public void run() {
            for (int i = 0; i < TURNS_COUNT; i++) {
                synchronized (ball) {
                    try {
                        while(amIPingOrPong != isPingTurn) {
                            ball.wait();
                        }
                        Thread.sleep(100);
                        ball.hit(name, i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // says to the other it is his turn now...
                        isPingTurn = !amIPingOrPong;
                        ball.notifyAll();
                    }
                }
            }
        }
    }
}

//        Thread-0: ping #0
//        Thread-1: pong #0
//        Thread-0: ping #1
//        Thread-1: pong #1
//        Thread-0: ping #2
//        Thread-1: pong #2
//        Thread-0: ping #3
//        Thread-1: pong #3
//        Thread-0: ping #4
//        Thread-1: pong #4
//        Thread-0: ping #5
//        Thread-1: pong #5
//        Thread-0: ping #6
//        Thread-1: pong #6
//        Thread-0: ping #7
//        Thread-1: pong #7
//        Thread-0: ping #8
//        Thread-1: pong #8
//        Thread-0: ping #9
//        Thread-1: pong #9
//        Game over, man...
