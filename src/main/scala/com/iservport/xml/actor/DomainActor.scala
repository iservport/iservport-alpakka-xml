package com.iservport.xml.actor

import akka.actor.{Actor, ActorLogging, Props}
import com.mongodb.casbah.{MongoClient, MongoDB}

/**
  * Created by mauriciofernandesdecastro on 29/05/17.
  */
class DomainActor extends Actor with ActorLogging {

  lazy val collectionActor = context.actorOf(Props(new CollectionActor()) , "_collection")

  override def receive: Receive = {
    case ("Combustivel", attributes: Map[String, String]) =>
      collectionActor ! CityData(attributes)
      collectionActor ! EntityData(attributes)
      collectionActor ! Vehicle(attributes)
      collectionActor ! Usage(attributes)
    case ("QuantitativaCombustivel", attributes: Map[String, String]) =>
      collectionActor ! Quantity(attributes)
    case ("VeiculoEquipamento", attributes: Map[String, String]) =>
      collectionActor ! VehicleData(attributes)
    case other => println(other)
  }

}
