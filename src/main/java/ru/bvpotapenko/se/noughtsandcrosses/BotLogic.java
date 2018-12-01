package ru.bvpotapenko.se.noughtsandcrosses;

public class BotLogic {
    //TODO:
    //Проверка угрозы поражения
    //проверить линии на наличие двух "Х"
    //Поставить "О" в свободную ячейку этой линии.

    //Проверяем построчно, победу:
    //Найти строку/столбец/диагональ с 2х "О" .. 1х "О"
    //Если такой линии нет, то найти строку без "Х"
    //Найти в строке свободный символ.
    //Поставить "О" в найденную позицию.


    //Усиление логики
    //1. Ставить в центр, если он свободен
    //2. Ставя "О" в пустую линию или линию с 1х "Х", выбирать ячейку рядом с "Х".
    //3. Искать выгодные пересечения строк, в которых есть "О"
    //4. Искать выгодные ходы (пересечения строк) для противника
    //5. Использовать один экземпляр класса, дополняя ходами.


    //Реализация:
    //Вариант 1. (масштабируется)
    //Можно хранить набор правил куда лучше ставить "О"
    //По мере заполнения поля понижать приоритет для строк, столбцов и диагоналей, которые не перспективны.
    // ...Их не рассматривать, или рассматривать в последнюю очередь.

    //TODO Вариант 2. (3х3)
    //Проверять перебором в порядке приоритета:
    // --гарантированная победа,
    // --гарантированный проигрыш,
    // --линия, где уже есть "О", но нет "Х",
    // --пустая линия

    //1. Нашли первую подходящую линию в порядке убывания приоритета.
    //      --Метод может возвращать тру/фолс и класть линию в поле.
    //2. Нашли первую свободную ячейку в клетке
    //3. Вернули координаты клетки Х и У.

    //Что такое Линия:
    //- Тип: строка, столбец, г.диагональ, п.диагональ
    //- Номер строки/столбца

    private int x;
    private int y;
    private int[][] map;
    private Line line;

    //todo
    private boolean getWinLIne(){
        return false;
    }

    //todo
    private boolean getLooseLine(){
        return false;
    }

    //todo
    private boolean get2DotLine(){
        return false;
    }

    //todo
    private boolean get1DotLine(){
        return false;
    }

    //todo
    private boolean getEmptyLine(){
        return false;
    }

    //todo
    private void setXY(){

    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public BotLogic(int[][] map) {
        this.map = map;
    }

    class Line{
        LineType type;
        int lineNumber;

        public Line(LineType type, int lineNumber) {
            this.type = type;
            this.lineNumber = lineNumber;
        }

        public LineType getType() {
            return type;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }

    enum LineType{
        ROW,
        COLUMN,
        MAIN_DIAGONAL,
        SECONDARY_DIAGONAL
    }




}
