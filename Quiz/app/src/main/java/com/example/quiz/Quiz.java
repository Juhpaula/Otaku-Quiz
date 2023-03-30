package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    ArrayList<Questao> questoes = new ArrayList<Questao>();
    TextView tvQuestao;
    ImageView ivImagem;
    Button btnAlternativa01, btnAlternativa02, btnAlternativa03, btnAlternativa04, btnVoltar;
    int contadorQuestoes = 0, acertos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ivImagem = (ImageView) findViewById(R.id.ivImagem);
        tvQuestao = (TextView) findViewById (R.id.tvQuestao);
        btnAlternativa01 = (Button) findViewById(R.id.btnAlternativa01);
        btnAlternativa02 = (Button) findViewById(R.id.btnAlternativa02);
        btnAlternativa03 = (Button) findViewById(R.id.btnAlternativa03);
        btnAlternativa04 = (Button) findViewById(R.id.btnAlternativa04);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnAlternativa01.setOnClickListener(verificarAlternativa);
        btnAlternativa02.setOnClickListener(verificarAlternativa);
        btnAlternativa03.setOnClickListener(verificarAlternativa);
        btnAlternativa04.setOnClickListener(verificarAlternativa);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        criarQuestoes();
        montarQuestoes();
    }
    public View.OnClickListener verificarAlternativa = new View.OnClickListener(){
        public void onClick(View view){
            String alternativaEscolhida;
            String btnClicado = String.valueOf(view);
            if(btnClicado.contains("btnAlternativa01")){
                alternativaEscolhida = String.valueOf(btnAlternativa01.getText());
            }else if(btnClicado.contains("btnAlternativa02")){
                alternativaEscolhida = String.valueOf(btnAlternativa02.getText());
            }else if(btnClicado.contains("btnAlternativa03")){
                alternativaEscolhida = String.valueOf(btnAlternativa03.getText());
            }else{
                alternativaEscolhida = String.valueOf(btnAlternativa04.getText());
            }
            if(alternativaEscolhida == questoes.get(contadorQuestoes).alternativaCorreta){
                acertos++;
                mudarCorBotao(btnClicado, "acerto");
                contadorQuestoes++;
                if(contadorQuestoes < questoes.size()){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            montarQuestoes();
                        }
                    }, 800);
                }else{
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            montarPopup(view);
                        }
                    }, 800);
                }
            }else{
                mudarCorBotao(btnClicado, "erro");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        montarPopup(view);
                    }
                }, 800);
            }
        }
    };
    public void mudarCorBotao(String btnClicado, String result){
        if(btnClicado.contains("btnAlternativa01")){
            if(result.equals("acerto")){
                btnAlternativa01.setBackgroundResource(R.drawable.alternativas_estilo_acerto);
            }else{
                btnAlternativa01.setBackgroundResource(R.drawable.alternativas_estilo_erro);
            }
        }else if(btnClicado.contains("btnAlternativa02")){
            if(result.equals("acerto")){
                btnAlternativa02.setBackgroundResource(R.drawable.alternativas_estilo_acerto);
            }else{
                btnAlternativa02.setBackgroundResource(R.drawable.alternativas_estilo_erro);
            }
        }else if(btnClicado.contains("btnAlternativa03")){
            if(result.equals("acerto")){
                btnAlternativa03.setBackgroundResource(R.drawable.alternativas_estilo_acerto);
            }else{
                btnAlternativa03.setBackgroundResource(R.drawable.alternativas_estilo_erro);
            }
        }else {
            if (result.equals("acerto")) {
                btnAlternativa04.setBackgroundResource(R.drawable.alternativas_estilo_acerto);
            } else {
                btnAlternativa04.setBackgroundResource(R.drawable.alternativas_estilo_erro);
            }
        }
    }
    public void montarQuestoes(){
        ivImagem.setImageResource(questoes.get(contadorQuestoes).imagemAnime);
        tvQuestao.setText(questoes.get(contadorQuestoes).questao);
        btnAlternativa01.setText(questoes.get(contadorQuestoes).alternativas.get(0));
        btnAlternativa01.setBackgroundResource(R.drawable.alternativas_estilo);
        btnAlternativa02.setText(questoes.get(contadorQuestoes).alternativas.get(1));
        btnAlternativa02.setBackgroundResource(R.drawable.alternativas_estilo);
        btnAlternativa03.setText(questoes.get(contadorQuestoes).alternativas.get(2));
        btnAlternativa03.setBackgroundResource(R.drawable.alternativas_estilo);
        btnAlternativa04.setText(questoes.get(contadorQuestoes).alternativas.get(3));
        btnAlternativa04.setBackgroundResource(R.drawable.alternativas_estilo);
    }
    public void criarQuestoes(){
        Questao novaQuestao1 = new Questao();
        novaQuestao1.imagemAnime = R.drawable.pokemon;
        novaQuestao1.questao = "Qual desses pokemons é do tipo elétrico?";
        novaQuestao1.alternativas.add(0,"Jolteon");
        novaQuestao1.alternativas.add(1,"Leafeon");
        novaQuestao1.alternativas.add(2,"Vaporeon");
        novaQuestao1.alternativas.add(3,"Umbreon");
        novaQuestao1.alternativaCorreta = "Jolteon";
        questoes.add(novaQuestao1);

        Questao novaQuestao2 = new Questao();
        novaQuestao2.imagemAnime = R.drawable.naruto;
        novaQuestao2.questao = "Contra quem Rock Lee lutou na segunda fase do exame chunnin?";
        novaQuestao2.alternativas.add(0, "Sakura");
        novaQuestao2.alternativas.add(1,"Naruto");
        novaQuestao2.alternativas.add(2,"Ino");
        novaQuestao2.alternativas.add(3,"Gaara");
        novaQuestao2.alternativaCorreta = "Gaara";
        questoes.add(novaQuestao2);

        Questao novaQuestao3 = new Questao();
        novaQuestao3.imagemAnime = R.drawable.one_piece;
        novaQuestao3.questao = "Qual o foi 3° membro da tripulação Mugiwara que Lufy conheceu?";
        novaQuestao3.alternativas.add(0,"Usopp");
        novaQuestao3.alternativas.add(1,"Zoro");
        novaQuestao3.alternativas.add(2,"Nami");
        novaQuestao3.alternativas.add(3,"Law");
        novaQuestao3.alternativaCorreta = "Nami";
        questoes.add(novaQuestao3);

        Questao novaQuestao4 = new Questao();
        novaQuestao4.imagemAnime = R.drawable.naruto;
        novaQuestao4.questao = "Sasuke Uchiha se voltou contra a vila e queria destrui-la, por qual motivo?";
        novaQuestao4.alternativas.add(0,"Para conseguir poder");
        novaQuestao4.alternativas.add(1,"Para vingar seu irmão Itachi");
        novaQuestao4.alternativas.add(2,"Para cumprir os objetivos de Orochimaru");
        novaQuestao4.alternativas.add(3,"Nenhum motivo especial");
        novaQuestao4.alternativaCorreta = "Para vingar seu irmão Itachi";
        questoes.add(novaQuestao4);

        Questao novaQuestao5 = new Questao();
        novaQuestao5.imagemAnime = R.drawable.one_piece;
        novaQuestao5.questao = "Qual o primeiro general doce foi visto na ilha Whole Cake";
        novaQuestao5.alternativas.add(0,"Katakuri");
        novaQuestao5.alternativas.add(1,"Snack");
        novaQuestao5.alternativas.add(2,"Cracker");
        novaQuestao5.alternativas.add(3,"Smoothie");
        novaQuestao5.alternativaCorreta = "Cracker";
        questoes.add(novaQuestao5);

        Questao novaQuestao6 = new Questao();
        novaQuestao6.imagemAnime = R.drawable.pokemon;
        novaQuestao6.questao = "Qual desses pokemons é de Johto";
        novaQuestao6.alternativas.add(0,"Lanturn");
        novaQuestao6.alternativas.add(1,"Alakazam");
        novaQuestao6.alternativas.add(2,"Lapras");
        novaQuestao6.alternativas.add(3,"Arcanine");
        novaQuestao6.alternativaCorreta = "Lanturn";
        questoes.add(novaQuestao6);
    }
    public void montarPopup(View view){
        LayoutInflater inflater = (LayoutInflater)
                view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView tvMensagem = (TextView) popupView.findViewById(R.id.tvMensagem);
        ImageView ivImagemPopup = (ImageView) popupView.findViewById(R.id.ivImagemPopup);
        if(contadorQuestoes < questoes.size()){
            tvMensagem.setText("Que pena, você errou!");
            ivImagemPopup.setImageResource(R.drawable.anya_error);
            popupView.setBackgroundResource(R.drawable.popup_estilo_erro);
        }else{
            tvMensagem.setText("Parabens, você acertou todas!");
            ivImagemPopup.setImageResource(R.drawable.anya_acerto);
            popupView.setBackgroundResource(R.drawable.popup_estilo_certo);
        }

        TextView tvAcertos = (TextView) popupView.findViewById(R.id.tvAcertos);
        tvAcertos.setText("Acertos: " + acertos);

        Button btnVoltar = (Button) popupView.findViewById(R.id.btnVoltarPopup);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}