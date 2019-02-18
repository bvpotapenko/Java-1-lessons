package ru.bvpotapenko.se.noughtsandcrosses;

import java.text.MessageFormat;
import java.util.*;

public class BotLogic {
    /**
     * Check threats:
     * One turn guarantee loose:
     * - There are [dotsToWin - 1] player's tokens with one free cell nearby or among them
     * Two turns guarantee loose (traps):
     * - There are [dotsToWin - 2] player's tokens with total of three free cells around them. Ex: (2)(1)xxx(2)     *
     * - Проверка более сложного условия хх(2)х(1)(2)хх
     * - Reciprocally one- two- guarantee win situations
     * - Plays from center
     */


    /**
     * TODO: 17-Feb-19  Усиление логики
     * 3. Искать выгодные пересечения строк, в которых есть "dotBot"
     * 4. Искать и блокировать выгодные ходы (пересечения строк) для противника
     * 5. Выбирать шаги для условия "Гарантированная победа за 1-2 шага"
     */

    private Position curPosition;
    private char[][] map;
    private char dotEmpty;
    private char dotPlayer;
    private char dotBot;
    private int dotsToWin;
    private int mapWidth;
    private int mapHeight;
    private Set<Strategy> strategies;

    public BotLogic(char[][] map, char dotEmpty, char dotBot, char dotPlayer, int dotsToWin) {
        this.map = map;
        this.dotEmpty = dotEmpty;
        this.dotBot = dotBot;
        this.dotPlayer = dotPlayer;
        this.dotsToWin = dotsToWin;
        this.curPosition = new Position();
        this.mapWidth = map.length;
        this.mapHeight = map[0].length;
        strategies = new TreeSet<>(Comparator.comparingInt(a -> a.priority));
        initStrategies();
    }

    private void initStrategies() {
        String rule;
        //One Turn win
        rule = MessageFormat.format("^[{0}]*({1})[{0}]*$", dotBot, dotEmpty);
        //strategies.add(new Strategy("^[" + dotBot + "]*(" + dotEmpty + ")[" + dotBot + "]*$", dotsToWin, 0));
        strategies.add(new Strategy(rule, dotsToWin, 0));
        //One Turn loose
        rule = MessageFormat.format("^[{0}]*({1})[{0}]*$", dotPlayer, dotEmpty);
        //strategies.add(new Strategy("^[" + dotPlayer + "]*(" + dotEmpty + ")[" + dotPlayer + "]*$", dotsToWin, 10));
        strategies.add(new Strategy(rule, dotsToWin, 10));

        //Two Turns Guarantee win
        /**
         * 1. Space around. Example: (2)xxx(1)(2)
         * if we play "X" on position (1) then we guarantee win next turn (if more priority strategies failed).
         */
        rule = MessageFormat.format("^({0})+[{1}]'{'{2}'}'({0})+$", dotEmpty, dotBot, dotsToWin - 2);
        // Strategy s = new Strategy("^(" + dotEmpty + ")+[" + dotBot + "]{" + (dotsToWin - 2) + "}( " + dotEmpty + ")+$",
        Strategy s = new Strategy(rule, dotsToWin + 1, 20);
        s.setDotPosition(dotsToWin-1);
        s.setUseDotPosition(true);
        s.setCheckReverseString(true);
        strategies.add(s);
        /**
         * 2. Example: xx(2)x(1)(2)xx
         * if we play "X" on position (1) then we guarantee win next turn (if more priority strategies failed).
         */
        for (int i = 0; i < dotsToWin - 3; i++) {
            s = new Strategy("^[" + dotBot + "]{" + (i + 1) + "}(" + dotEmpty + "){1}[" + dotBot + "]{" + (dotsToWin - 3 - i) + "}(" + dotEmpty + "){2}[" + dotBot + "]+$",
                    2 * dotsToWin - 1, 20);
            s.setDotPosition(dotsToWin-1);
            s.setUseDotPosition(true);
            s.setCheckReverseString(true);
            strategies.add(s);
        }
        /**
         * 3. Example: (2)xx(1)x(2)
         */
        for (int i = 0; i < dotsToWin - 3; i++) {
            rule = MessageFormat.format("^[{0}]+[{1}]'{'{2}'}'[{0}]'{'1'}'[{1}]'{'{3}'}'[{0}]+$",dotEmpty, dotBot, (dotsToWin-3 - i + 1), (i + 1));
            s = new Strategy(rule,dotsToWin + 1, 30);
            s.setDotPosition(dotsToWin-3 - i + 1);
            s.setUseDotPosition(true);
            strategies.add(s);
        }
        /**
         * Two Turns Guarantee loose
         * 1. Symmetrical to "Two Turns Guarantee win"
         */
        s = new Strategy("^(" + dotEmpty + ")+[" + dotPlayer + "]{" + (dotsToWin - 2) + "}(" + dotEmpty + ")+$",
                dotsToWin + 1, 30);
        s.setDotPosition(dotsToWin-1);
        s.setUseDotPosition(true);
        s.setCheckReverseString(true);
        strategies.add(s);
        /**
         * 2. Example: xx(2)x(1)(2)xx
         * if user plays "X" on position (1) then he guarantee wins next turn (if more priority strategies failed).
         */
        for (int i = 0; i < dotsToWin - 3; i++) {
            s = new Strategy("^[" + dotPlayer + "]{" + (i + 1) + "}(" + dotEmpty + "){1}[" + dotPlayer + "]{" + (dotsToWin - 3 - i) + "}(" + dotEmpty + "){2}[" + dotPlayer + "]+$",
                    2 * dotsToWin - 1, 30);
            s.setDotPosition(dotsToWin-1);
            s.setUseDotPosition(true);
            s.setCheckReverseString(true);
            strategies.add(s);
        }
        /**
         * 3. Example: (2)xx(1)x(2)
         */
        for (int i = 0; i < dotsToWin - 3; i++) {
            rule = MessageFormat.format("^[{0}]+[{1}]'{'{2}'}'[{0}]'{'1'}'[{1}]'{'{3}'}'[{0}]+$",dotEmpty, dotPlayer, (dotsToWin-3 - i + 1), (i + 1));
            s = new Strategy(rule,dotsToWin + 1, 30);
            s.setDotPosition(dotsToWin-3 - i + 1);
            s.setUseDotPosition(true);
            strategies.add(s);
        }
        /**
         * Plays from the center of the map
         */
        if (mapWidth % 2 == 1) {
            //Horizontal lines
            s = new Strategy("^.{" + (mapWidth / 2 - 1) + "}[" + dotEmpty + "]{1}.{" + (mapWidth / 2 - 1) + "}$", mapWidth, 40);
            s.setDotPosition(mapWidth / 2);
            s.setUseDotPosition(true);
            s.setHorizontalLinesOnly(true);
            s.setInitialY(mapWidth / 2);
            strategies.add(s);
            //Vertical lines
            s = new Strategy("^.{" + (mapHeight / 2 - 1) + "}[" + dotEmpty + "]{1}.{" + (mapHeight / 2 - 1) + "}$", mapHeight, 40);
            s.setDotPosition(mapHeight / 2);
            s.setUseDotPosition(true);
            s.setVerticalLinesOnly(true);
            s.setInitialX(mapWidth / 2);
            strategies.add(s);
        }
        //One extra occasion of the rule for a map with even columns/rows amount
        if (mapWidth % 2 == 0) {
            //Horizontal lines
            s = new Strategy("^.{" + (mapWidth / 2 - 1) + "}[" + dotEmpty + "]+.{" + (mapWidth / 2 - 1) + "}$", mapWidth, 40);
            s.setDotPosition(mapWidth / 2 - 1);
            s.setUseDotPosition(true);
            s.setHorizontalLinesOnly(true);
            s.setInitialY(mapWidth / 2 - 1);
            strategies.add(s);
            //Vertical lines
            s = new Strategy("^.{" + (mapHeight / 2 - 1) + "}[" + dotEmpty + "]+.{" + (mapHeight / 2 - 1) + "}$", mapHeight, 40);
            s.setDotPosition(mapHeight / 2 - 1);
            s.setUseDotPosition(true);
            s.setVerticalLinesOnly(true);
            s.setInitialX(mapWidth / 2 - 1);
            strategies.add(s);
        }
    }

    /**
     * Looks for the first line of "dotsToWin" length on the game map that satisfies the rule (a regular expression).
     * Sets "currPosition" coordinates according to the rule.
     *
     * @param strategy - A class with a regular expression to be satisfied
     * @return - true or false if a line found or not.
     */
    private boolean useTheRule(Strategy strategy) {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                //Check initial line position requirements for Strategy
                if (strategy.initialX > 0 && strategy.initialX < mapWidth - 1
                        && strategy.initialX != i) continue;
                if (strategy.initialY > 0 && strategy.initialY < mapHeight - 1
                        && strategy.initialY != j) continue;

                curPosition.x = i;
                curPosition.y = j;
                //Horizontal line
                if (!strategy.verticalLinesOnly) {
                    String horizontalLine = getLine(curPosition, strategy.lineLength, 1, 0);
                    if (horizontalLine != null && horizontalLine.matches(strategy.rule)) {
                        if (setPosition(strategy, horizontalLine, 1, 0))
                            return true;
                    } else if (horizontalLine != null && strategy.checkReverseString && checkReversed(horizontalLine, strategy)) {
                        if (serReversedPosition(strategy, horizontalLine, 1, 0))
                            return true;
                    }
                }
                if (!strategy.horizontalLinesOnly) {
                    //Vertical line
                    String verticallLine = getLine(curPosition, strategy.lineLength, 0, 1);
                    if (verticallLine != null && verticallLine.matches(strategy.rule)) {
                        if (setPosition(strategy, verticallLine, 0, 1))
                            return true;
                    } else if (verticallLine != null && strategy.checkReverseString && checkReversed(verticallLine, strategy)) {
                        if (serReversedPosition(strategy, verticallLine, 0, 1))
                            return true;
                    }
                }
                if (!strategy.horizontalLinesOnly && !strategy.verticalLinesOnly) {
                    //Main diagonals
                    String mainDiagonalLine = getLine(curPosition, strategy.lineLength, 1, 1);
                    if (mainDiagonalLine != null && mainDiagonalLine.matches(strategy.rule)) {
                        if (setPosition(strategy, mainDiagonalLine, 1, 1))
                            return true;
                    } else if (mainDiagonalLine != null && strategy.checkReverseString && checkReversed(mainDiagonalLine, strategy)) {
                        if (serReversedPosition(strategy, mainDiagonalLine, 1, 1))
                            return true;
                    }

                    //Secondary diagonals
                    String secondDiagonalLine = getLine(curPosition, strategy.lineLength, -1, 1);
                    if (secondDiagonalLine != null && secondDiagonalLine.matches(strategy.rule)) {
                        if (setPosition(strategy, secondDiagonalLine, -1, 1))
                            return true;
                    } else if (secondDiagonalLine != null && strategy.checkReverseString && checkReversed(secondDiagonalLine, strategy)) {
                        if (serReversedPosition(strategy, secondDiagonalLine, -1, 1))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private String getLine(Position initialPosition, int lineLength, int horizontalOffset, int verticalOffset) {
        StringBuilder sb = new StringBuilder(lineLength);
        for (int i = 0; i < dotsToWin; i++) {
            int x = initialPosition.x + horizontalOffset * i;
            int y = initialPosition.y + verticalOffset * i;
            if (x >= map.length || x < 0 || y >= map[0].length) {
                return null;
            } else {
                sb.append(map[x][y]);
            }
        }
        return sb.toString();
    }

    private boolean checkReversed(String lineToReverse, Strategy s) {
        return new StringBuffer(lineToReverse).reverse().toString().matches(s.rule);
    }

    private boolean setPosition(Strategy str, String line, int offX, int offY) {
        if (str.useDotPosition) {
            curPosition.x += offX * str.dotPosition;
            curPosition.y += offY * str.dotPosition;
        } else {
            curPosition.x += offX * line.indexOf(dotEmpty);
            curPosition.y += offY * line.indexOf(dotEmpty);
        }
        if (str.isServiceRule || isCellEmpty(curPosition)) {
            return true;
        }
        return false;
    }

    private boolean serReversedPosition(Strategy str, String line, int offX, int offY) {
        if (str.useDotPosition) {
            curPosition.x += offX * (str.lineLength - str.dotPosition - 1);
            curPosition.y += offY * (str.lineLength - str.dotPosition - 1);
        } else {
            curPosition.x += offX * (str.lineLength - line.indexOf(dotEmpty) - 1);
            curPosition.y += offY * (str.lineLength - line.indexOf(dotEmpty) - 1);
        }
        if (str.isServiceRule || isCellEmpty(curPosition))
            return true;
        return false;
    }

    private void setRndPosition() {
        Random random = new Random();
        do {
            curPosition.x = random.nextInt(map.length);
            curPosition.y = random.nextInt(map[0].length);
        } while (!isCellEmpty(curPosition));
    }

    public int getX() {
        return curPosition.x;
    }

    public int getY() {
        return curPosition.y;
    }


    public void processNextStep() {
        System.out.println("Bot is thinking..");
        Iterator<Strategy> itr = strategies.iterator();
        boolean ruleUsed = false;
        while (itr.hasNext() && !ruleUsed) {
            if (useTheRule(itr.next()))
                ruleUsed = true;
        }
        if (!ruleUsed) {
            setRndPosition();
        }
    }

    private boolean isCellEmpty(Position pos) {
        if (pos.x < 0 || pos.y < 0 || pos.x > map.length - 1 || pos.y > map[0].length - 1)
            return false;
        return map[pos.x][pos.y] == dotEmpty;
    }

    public boolean checkWin(char[][] map, char dot) {
        String winRule = "^[" + dot + "]+$";
        Strategy s = new Strategy(winRule, dotsToWin, 1);
        s.setUseDotPosition(true);
        s.setDotPosition(0);
        s.setServiceRule(true);
        return useTheRule(s);
    }

    public void updateMap(char[][] map) {
        this.map = map;
    }

    class Position {
        int x;
        int y;
    }

    /**
     * Strategies with minimal "priority" are used first.
     * This class must be improved with additional parameters to satisfy more complicated rules
     * "rule" - regExp
     * "lineLength" - the length of a line to evaluate
     * "priority" - Order of this rule in execution line. The greater - the less important.
     * "dotPosition" - exact position in the line where to place the token.
     * "useDotPosition" - denotes necessity to use exact "dotPosition" or look for first occurrence.
     * "checkReverseString" - denotes necessity to apply this rule to a reversed string as well
     * "initialX" - this rule applies only for certain initial position X
     * "initialY" - this rule applies only for certain initial position Y
     * "isServiceRule" - Service rules only check conditions, aren't used to define next move.
     */
    class Strategy {
        String rule;
        int lineLength;
        int priority;
        int dotPosition;
        boolean useDotPosition;
        boolean checkReverseString;
        int initialX;
        int initialY;
        boolean verticalLinesOnly;
        boolean horizontalLinesOnly;
        boolean isServiceRule;

        public Strategy(String rule, int lineLength, int priority) {
            this.rule = rule;
            this.lineLength = lineLength;
            this.priority = priority;
            this.useDotPosition = false;
            this.checkReverseString = false;
            this.horizontalLinesOnly = false;
            this.verticalLinesOnly = false;
        }

        public void setDotPosition(int dotPosition) {
            this.dotPosition = dotPosition;
        }

        public void setUseDotPosition(boolean useDotPosition) {
            this.useDotPosition = useDotPosition;
        }

        public void setCheckReverseString(boolean checkReverseString) {
            this.checkReverseString = checkReverseString;
        }

        public void setInitialX(int initialX) {
            this.initialX = initialX;
        }

        public void setInitialY(int initialY) {
            this.initialY = initialY;
        }

        public void setVerticalLinesOnly(boolean verticalLinesOnly) {
            this.verticalLinesOnly = verticalLinesOnly;
        }

        public void setHorizontalLinesOnly(boolean horizontalLinesOnly) {
            this.horizontalLinesOnly = horizontalLinesOnly;
        }

        public void setServiceRule(boolean b) {
            this.isServiceRule = b;
        }
    }

}
