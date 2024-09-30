package unipar.edu.taffebank

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val transacoes = mutableListOf<Transacao>()
    private lateinit var adapter: TransacaoAdapter
    private lateinit var rvTransacoes: RecyclerView
    private lateinit var tvSaldo: TextView
    private var saldoTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSaldo = findViewById(R.id.tvSaldo)
        rvTransacoes = findViewById(R.id.recyclerView)
        val spinnerTipo: Spinner = findViewById(R.id.spinnerTipo)
        val etValor: EditText = findViewById(R.id.etValor)
        val etDescricao: EditText = findViewById(R.id.etDescricao)
        val btnAdicionar: Button = findViewById(R.id.btnAdicionar)

        val tipos = arrayOf("Crédito", "Débito")
        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        adapter = TransacaoAdapter(transacoes)
        rvTransacoes.adapter = adapter
        rvTransacoes.layoutManager = LinearLayoutManager(this)

        btnAdicionar.setOnClickListener {
            val tipo = spinnerTipo.selectedItem.toString()
            val valor = etValor.text.toString().toDoubleOrNull()
            val descricao = etDescricao.text.toString()

            if (valor != null && descricao.isNotEmpty()) {
                val transacao = Transacao(tipo, valor, descricao)
                transacoes.add(transacao)

                saldoTotal += if (tipo == "Crédito") valor else -valor
                tvSaldo.text = String.format("Saldo: R$ %.2f", saldoTotal)

                adapter.notifyDataSetChanged()

                etValor.text.clear()
                etDescricao.text.clear()
            }
        }
    }
}

