package com.example.receitas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.receitas.databinding.FragmentoMenuPrinciaplBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuPrincipalFragmento : Fragment() {

    private var _binding: FragmentoMenuPrinciaplBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentoMenuPrinciaplBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSobre.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_SobreFragment)
        }

        binding.buttonReceitas.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_listaReceitasFragmento)
        }
        binding.buttonTipoReceitas.setOnClickListener{
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_listaTipoDeReceita)
        }
        binding.buttonPesquisar.setOnClickListener{
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_pesquisa_Fragment)
        }
        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}