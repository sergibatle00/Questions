package com.example.questions;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private static int puntuation = 0;
    private static int currentQuestionIndex = -1; // Inicialmente, no hay ninguna pregunta actual

    // Lista de preguntas
    private static List<Question> questions = new ArrayList<>();

    // Método para agregar una pregunta
    public static void addQuestion(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        questions.add(new Question(question, optionA, optionB, optionC, optionD, correctAnswer));
    }

    // Constructor
    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
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
    public static boolean isCorrectAnswer(String answer) {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex).correctAnswer.equalsIgnoreCase(answer);
        }
        return false; // No hay pregunta actual
    }
}
