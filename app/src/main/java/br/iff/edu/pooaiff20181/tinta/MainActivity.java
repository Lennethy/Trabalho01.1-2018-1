package br.iff.edu.pooaiff20181.tinta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etM2;
    private Button btCalcular;
    private TextView tvLata;
    private TextView tvGalao;
    private TextView tvCombo;

    private int qtdLata;
    private int qtdGalao;
    private int comboLata;
    private int comboGalao;
    private float litrosRestanteL;
    private float litrosRestanteG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etM2 = (EditText) findViewById(R.id.etM2);
        btCalcular = (Button) findViewById(R.id.btCalcTint);
        tvLata = (TextView) findViewById(R.id.tvLata);
        tvGalao = (TextView) findViewById(R.id.tvGalao);
        tvCombo = (TextView) findViewById(R.id.tvCombo);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
                msgResultado();
            }
        });

    }

    private void calcular(){
        float metros = Float.parseFloat(etM2.getText().toString());
        float litros = (metros/6);

        qtdLata = (int) (litros / 18);
        qtdGalao = (int) (litros/3.6);
        litrosRestanteL = (litros % 18);
        litrosRestanteG =(float) (litros % 3.6);

        if(litrosRestanteL != 0){
            qtdLata += 1;
        }
        if(litrosRestanteG != 0){
            qtdGalao += 1;
        }

        if(litros > 18){
            comboLata =(int) (litros/18);

            if(litrosRestanteL > 10.8){
                comboLata += 1;
            }else{
                comboGalao = (int)(litrosRestanteL / 3.6);
                if(litrosRestanteL != 0){
                    comboGalao += 1;
                }
            }
        }else{
            if(litros <= 10.8){
                comboGalao =(int) (litros / 3.6);
                if(litrosRestanteG != 0){
                    comboGalao += 1;
                }
            }else{
                comboLata += 1;
            }
        }
    }

    private void msgResultado(){

        tvLata.setText("Latas apenas: " + qtdLata +" latas.\nValor:R$ "+ (qtdLata * 80));
        tvGalao.setText("Galões apenas: " + qtdGalao +" galões.\nValor:R$ "+ (qtdGalao * 25));
        tvCombo.setText("Combinação: " + comboLata +" latas e " + comboGalao +" galões \nno valor de R$ " +((comboLata * 80) + (comboGalao * 25)));
    }
}
