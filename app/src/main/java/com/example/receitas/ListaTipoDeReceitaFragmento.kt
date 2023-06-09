package com.example.receitas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receitas.databinding.FragmentListaTipoDeReceitaBinding

private const val ID_LOADER_TIPODERECEITAS = 0
class ListaTipoDeReceitaFragmento : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaTipoDeReceitaBinding? = null
    private val binding get() = _binding!!

    var tipoDeReceitaSelecionado : TipoDeReceita? = null
        set(value){
            field = value

            val mostrarEliminarAlterar = (value != null)
            val activity = activity as MainActivity
            activity.mostraBotaoMenu(R.id.action_editar,mostrarEliminarAlterar)
            activity.mostraBotaoMenu(R.id.action_eliminar,mostrarEliminarAlterar)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListaTipoDeReceitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var AdapterTipoDeReceitas: AdapterTipoDeReceitas? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AdapterTipoDeReceitas = AdapterTipoDeReceitas(this)
        binding.receyclerViewTipoDeReceita.adapter = AdapterTipoDeReceitas
        binding.receyclerViewTipoDeReceita.layoutManager = LinearLayoutManager(requireContext())

        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_TIPODERECEITAS,null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_receitas
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ReceitasContentProvider.ENDERECO_TIPODERECEITA,
            TabelaTipoDeReceitas.CAMPOS,
            null, null,
            TabelaTipoDeReceitas.CAMPO_NOME
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        AdapterTipoDeReceitas!!.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        AdapterTipoDeReceitas!!.cursor = data
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean{
        return when (item.itemId) {
            R.id.action_adicionar -> {
                adicionaTipoDeReceita()
                true
            }
            R.id.action_editar -> {
                editarTipoDeReceita()
                true
            }
            R.id.action_eliminar -> {
                eliminarTipoDeReceita()
                true
            }
            else -> false
        }
    }

    private fun eliminarTipoDeReceita() {
        val acao = ListaTipoDeReceitaFragmentoDirections.actionListaTipoDeReceitaToFragmentEliminarTipoDeReceita(tipoDeReceitaSelecionado!!)
        findNavController().navigate(acao)
    }

    private fun editarTipoDeReceita() {
        val acao = ListaTipoDeReceitaFragmentoDirections.actionListaTipoDeReceitaToEditarTipoDeReceita(tipoDeReceitaSelecionado!!)
        findNavController().navigate(acao)
    }

    private fun adicionaTipoDeReceita() {
        val acao = ListaTipoDeReceitaFragmentoDirections.actionListaTipoDeReceitaToEditarTipoDeReceita(null)
        findNavController().navigate(acao)
    }
}