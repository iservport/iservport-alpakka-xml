package com.iservport.xml.actor

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
  * Created by mauriciofernandesdecastro on 29/05/17.
  */
sealed trait DomainProtocol {
  val  json: String
}
case class CityData(attributes: Map[String, String]) extends DomainProtocol {
  val json = s"""{
                 |"_id": "${attributes.getOrElse("cdIBGE", "SemIBGE")}"
                 |,"cityId": "${attributes.getOrElse("cdIBGE", "SemIBGE")}"
                 |,"nmMunicipio": "${attributes.getOrElse("nmMunicipio", "")}"
                 |}""".stripMargin
}
case class EntityData(attributes: Map[String, String]) extends DomainProtocol {
  val json = s"""{
                 |"_id": "${attributes.getOrElse("idEntidade", "SemEntidade")}"
                 |,"cityId": "${attributes.getOrElse("cdIBGE", "SemIBGE")}"
                 |,"nmEntidade": "${attributes.getOrElse("nmEntidade", "")}"
                 |}""".stripMargin
}
case class Vehicle(attributes: Map[String, String]) extends DomainProtocol {
  val json = s"""{
                 |"_id": "${attributes.getOrElse("idVeiculoEquipamento", "")}"
                 |,"cityId": "${attributes.getOrElse("cdIBGE", "SemIBGE")}"
                 |,"idEntidade": "${attributes.getOrElse("idEntidade", "")}"
                 |,"cdBem": "${attributes.getOrElse("cdBem", "")}"
                 |,"dsTipoPropriedadeBem": "${attributes.getOrElse("dsTipoPropriedadeBem", "")}"
                 |,"nrPlaca": "${attributes.getOrElse("nrPlaca", "SEM PLAC")}"
                 |,"dsMarcaVeiculo": "${attributes.getOrElse("dsMarcaVeiculo", "")}"
                 |,"dsModeloFIPE": "${attributes.getOrElse("dsModeloFIPE", "")}"
                 |,"dsVeiculoEquipamento": "${attributes.getOrElse("dsVeiculoEquipamento", "")}"
                 |}""".stripMargin
}
case class Usage(attributes: Map[String, String]) extends DomainProtocol {
  val json = s"""{
                 |"_id": "${attributes.getOrElse("idVeiculoEquipamento", "")}:${attributes.getOrElse("DataReferencia", "")}"
                 |,"idVeiculoEquipamento": "${attributes.getOrElse("idVeiculoEquipamento", "")}"
                 |,"dsTiposObjetoDespesa": "${attributes.getOrElse("dsTiposObjetoDespesa", "Vazio")}"
                 |,"vlConsumo": ${attributes.getOrElse("vlConsumo", 0.0)}
                 |,"idTipoMedidor": "${attributes.getOrElse("idTipoMedidor", "SemMedidor")}"
                 |,"dsTipoMedidor": "${attributes.getOrElse("dsTipoMedidor", "")}"
                 |,"vlMedicao": ${attributes.getOrElse("vlMedicao", 0.0)}
                 |,"dataReferencia": "${attributes.getOrElse("DataReferencia", "")}"
                 |}""".stripMargin
}
case class Quantity(attributes: Map[String, String]) extends DomainProtocol {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
  def toMonth(date: String) = f"${LocalDate.parse(date, formatter).getMonthValue}%02d"
  val json = s"""{
                 |"_id": "${attributes.getOrElse("idEntidade", "SemEntidade")}:${attributes.getOrElse("dtLiquidacao", "0").stripSuffix("T00:00:00")}"
                 |,"idEntidade": "${attributes.getOrElse("idEntidade", "")}"
                 |,"year": "${attributes.getOrElse("nrAnoLiquidacao", "")}"
                 |,"month": "${toMonth(attributes.getOrElse("dtLiquidacao", ""))}"
                 |,"subject": "${attributes.getOrElse("dsTipoObjetoDespesa", "")}:${attributes.getOrElse("dsUnidadeMedida", "")}"
                 |,"averagePrice": ${attributes.getOrElse("vlPagamentoAtualizado", "0").toDouble / attributes.getOrElse("nrSomaQuantidadeAtualizada", "1").toDouble}
                 |}""".stripMargin
}

case class VehicleData(attributes: Map[String, String]) extends DomainProtocol {
  val json = s"""{
                   |"_id": "${attributes.getOrElse("nrRenavam", "")}"
                   |,"cdBem": "${attributes.getOrElse("cdBem", "")}"
                   |,"fuel": "${attributes.getOrElse("dsCombustivelVeiculoEq", "")}"
                   |}""".stripMargin
}