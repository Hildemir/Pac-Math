package  game;

import javafx.scene.canvas.GraphicsContext;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Expression {
    private Operacao op;
//    private final Dificuldade dificuldade;
    private int num1, num2, resultado;
    private String expr;
    private int [] numGerados = {0,0,0,0};


//    public void setExpression() {
//        gerarExpressao();
//        possiveisRespostas();
//    }

    private enum Operacao {
        SOMA("+"), SUBTRACAO("-"), MULTIPLICACAO("x"), DIVISAO("/");

        private String op;

        Operacao(String op) {
            this.op = op;
        }

        public String getOperador() {
            return op;
        }

        public static Operacao operadorAleatorio() {
            SecureRandom r = new SecureRandom();
            return values()[r.nextInt(values().length)];
        }
    }

    public Expression() {           // construtor  chama o metodo que gera a expressao ( numeros e operacoes) e chama possiveis respostas
        //this.op = Operacao.operadorAleatorio();
        gerarExpressao();
        this.numGerados = new int[4];
        possiveisRespostas();
    }

    public void draw(GraphicsContext gc) {
            gc.fillText(getExpressao(),1450,570);

    }

    public String format (int num) {
        return String.valueOf(num);
    }

    public void possiveisRespostas() {
        Random r = new Random();
        int num;
            if(resultado < 10 ){                //caso resultado seja < 10
                switch (resultado){
                    case 0:
                        numGerados[0] = resultado;
                        numGerados[1] = 2;
                        numGerados[2] = 3;
                        numGerados[3] = 2;
                        break;
                    case 1:
                        numGerados[0] = resultado;
                        numGerados[1] = 4;
                        numGerados[2] = 2;
                        numGerados[3] = 3;
                        break;
                    case 2:
                        numGerados[0] = 1;
                        numGerados[1] = 1;
                        numGerados[2] = 3;
                        numGerados[3] = 4;
                        break;
                    case 3:
                        numGerados[0] = 1;
                        numGerados[1] = 2;
                        numGerados[2] = 4;
                        numGerados[3] = 5;
                        break;
                    case 4:
                        numGerados[0] = 2;
                        numGerados[1] = 2;
                        numGerados[2] = 3;
                        numGerados[3] = 5;
                        break;
                    case 5:
                        numGerados[0] = 2;
                        numGerados[1] = 3;
                        numGerados[2] = 4;
                        numGerados[3] = 4;
                        break;
                    case 6:
                        numGerados[0] = 2;
                        numGerados[1] = 4;
                        numGerados[2] = 1;
                        numGerados[3] = 3;
                        break;
                    case 7:
                        numGerados[0] = 3;
                        numGerados[1] = 4;
                        numGerados[2] = 2;
                        numGerados[3] = 2;
                        break;
                    case 8:
                        numGerados[0] = 2;
                        numGerados[1] = 6;
                        numGerados[2] = 3;
                        numGerados[3] = 1;
                        break;
                    case 9:
                        numGerados[0] = 3;
                        numGerados[1] = 6;
                        numGerados[2] = 2;
                        numGerados[3] = 4;
                        break;
                }
            } else if (resultado < 40){             //caso resultado esteja entre 10 e 40
                int aux = resultado;

                num = 1 + r.nextInt(5);
                numGerados[0] = num;
                aux -= num;
                numGerados[1] = aux;
                numGerados[2] = numGerados[1] + numGerados[1]/10;
                if(numGerados[1] - (numGerados[1]%10 + numGerados[1]/10) > 0) {
                    numGerados[3] = numGerados[1] - (numGerados[1] % 10 + numGerados[1] / 10);
                } else {
                    numGerados[3] = numGerados[1] + (numGerados[1] % 10 + numGerados[1] / 10);
                }
            } else {
                int aux = resultado;
                num = 1 + r.nextInt(40);
                numGerados[0] = num;
                aux -= num;

                numGerados[1] = aux;
                numGerados[2] = numGerados[1] + numGerados[1] / 10;
                if (numGerados[1] - (numGerados[1] % 10 + numGerados[1] / 10) > 0) {
                    numGerados[3] = numGerados[1] - (numGerados[1] % 10 + numGerados[1] / 10);
                } else {
                    numGerados[3] = numGerados[1] + (numGerados[1] % 10 + numGerados[1] / 10);
                }
            }

        for(int i = 0; i < 4; i++) {
            int troca = numGerados[i];
            int i2 = r.nextInt(4);
            numGerados[i] = numGerados[i2];
            numGerados[i2] = troca;
        }
    }

    public void gerarExpressao() {                                             //metodo que gera a expressao
        // multiplica os numeros por dois se for adicao ou subtracao para
        // nao ficar muito facil
        this.op = Operacao.operadorAleatorio();
        gerarNumeros(10);

        if(op == Operacao.SOMA || op == Operacao.SUBTRACAO) {
            num1 <<= 1;
            num2 <<= 1;
        }

        // se for subtracao troca os numeros para impedir resultados negativos
        if(op == Operacao.SUBTRACAO && num2 > num1) {
            int troca = num1;
            num1 = num2;
            num2 = troca;

        } else if(op == Operacao.DIVISAO) {
            resultado = num1;
            num1 = resultado * num2;
        }
        expr = num1 + op.getOperador() + num2 + "=";

        resolucaoExpressao();
    }


    private void resolucaoExpressao() {     //metodo que resolve a expressao
        switch(op) {
            case SOMA:
                resultado = num1 + num2;
                break;
            case SUBTRACAO:
                resultado = num1 - num2;
                break;
            case MULTIPLICACAO:
                resultado = num1 * num2;
                break;
            default: // divisoes resolvidas dentro de gerarExpressao()
                break;
        }
    }

    private void gerarNumeros(int max) {            //metodo que gera os numeros
        SecureRandom s = new SecureRandom();
        num1 = s.nextInt(max) + 1;
        System.out.println(num1);
        num2 = s.nextInt(max) + 1;
        System.out.println(num2);
    }

    public String getExpressao() {          //retorna a expressao (String)
        return expr;
    }

    public int getResultado() {             //retorna o resultado da expressao
        return resultado;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public Operacao getOpearcao() {
        return op;
    }

    public int[] getNumGerados() {
        return numGerados;
    }

    public boolean resposta(int suaResposta) {
        return suaResposta == this.resultado;
    }

    public boolean resposta(String suaResposta) {
        return Integer.parseInt(suaResposta) == this.resultado;
    }

}