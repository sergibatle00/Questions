package com.example.questions;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String correctAnswer;
    public static int puntuation = 0;
    public static int currentQuestionIndex = -1; // Inicialmente, no hay ninguna pregunta actual

    // Lista de preguntas predefinidas
    private static List<Question> questions = new ArrayList<>();

    // Constructor
    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Método para agregar una pregunta predefinida
    public static void addQuestion(Question question) {
        questions.add(question);
    }

    // Método para avanzar a la siguiente pregunta
    public static Question getNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            return questions.get(currentQuestionIndex);
        }
        return null; // No hay más preguntas
    }

    // Método para verificar si la respuesta es correcta
    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    public static void increasePuntuation() {
        puntuation++;
    }

    public static void resetPoints() {
        puntuation = 0;
    }
}
