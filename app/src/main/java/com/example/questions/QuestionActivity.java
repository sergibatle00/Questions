package com.example.questions;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;
    private Button nextButton;
    private TextView pointsTextView;

    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Inicializa las vistas
        questionTextView = findViewById(R.id.questionTextView);
        radioGroup = findViewById(R.id.radioGroup);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        nextButton = findViewById(R.id.next);
        pointsTextView = findViewById(R.id.points);

        // Agrega las preguntas con sus respuestas predefinidas
        Question.addQuestion(new Question("What is the capital of Canada?", "Ottawa", "Toronto", "Vancouver", "Montreal", "Ottawa"));
        Question.addQuestion(new Question("Which planet is known as the Red Planet?", "Earth", "Mars", "Venus", "Jupiter", "Mars"));
        Question.addQuestion(new Question("Who wrote the play 'Romeo and Juliet'?", "Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain", "William Shakespeare"));
        Question.addQuestion(new Question("Which gas do plants absorb from the atmosphere?", "Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen", "Carbon dioxide"));
        Question.addQuestion(new Question("What is the largest mammal in the world?", "African Elephant", "Blue Whale", "Giraffe", "Polar Bear", "Blue Whale"));
        Question.addQuestion(new Question("What is the currency of Japan?", "Yen", "Won", "Rupee", "Dollar", "Yen"));
        Question.addQuestion(new Question("In which year did Christopher Columbus reach the Americas?", "1492", "1507", "1519", "1485", "1492"));
        Question.addQuestion(new Question("What is the chemical symbol for the element gold?", "Ag", "Au", "Ge", "Go", "Au"));
        Question.addQuestion(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Michelangelo", "Leonardo da Vinci"));
        Question.addQuestion(new Question("What is the largest ocean in the world?", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", "Pacific Ocean"));
        // Inicializa la primera pregunta
        showNextQuestion();

        // Configura el botón "Next" para avanzar a la siguiente pregunta
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifica si la respuesta es correcta
                String selectedOptionText = getSelectedOptionText();
                boolean isCorrect = currentQuestion.isCorrectAnswer(selectedOptionText);

                if (isCorrect) {
                    Question.increasePuntuation(); // Aumenta el puntaje si la respuesta es correcta
                }

                // Actualiza el puntaje en el TextView "points"
                pointsTextView.setText(String.valueOf(Question.puntuation));

                showNextQuestion();
            }
        });

        Button goHomeButton = findViewById(R.id.goHome);
        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navega de vuelta a la actividad principal (MainActivity)
                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual
            }
        });
    }

    // Método para mostrar la siguiente pregunta
    private void showNextQuestion() {
        currentQuestion = Question.getNextQuestion();

        if (currentQuestion != null) {
            questionTextView.setText(currentQuestion.question);
            optionA.setText("A) " + currentQuestion.optionA);
            optionB.setText("B) " + currentQuestion.optionB);
            optionC.setText("C) " + currentQuestion.optionC);
            optionD.setText("D) " + currentQuestion.optionD);
            pointsTextView.setText(String.valueOf(Question.puntuation));
        } else {
            questionTextView.setText("FIN");
            pointsTextView.setTextColor(Color.RED);
            // Desactiva el botón "Next" cuando no hay más preguntas
            nextButton.setEnabled(false);
            // Desactiva todas las opciones en el RadioGroup
            optionA.setEnabled(false);
            optionB.setEnabled(false);
            optionC.setEnabled(false);
            optionD.setEnabled(false);
        }
    }

    // Método para obtener la respuesta seleccionada
    private String getSelectedOptionText() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        if (selectedRadioButton != null) {
            return selectedRadioButton.getText().toString().substring(3); // Obtener el texto sin el número de opción
        }
        return null;
    }
}
