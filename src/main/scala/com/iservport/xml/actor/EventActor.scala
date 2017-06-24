package com.iservport.xml.actor

import akka.actor.{Actor, ActorLogging, Props}
import akka.stream.alpakka.xml._

/**
  * Created by mauriciofernandesdecastro on 29/05/17.
  */
class EventActor extends Actor with ActorLogging {

  lazy val domainActor = context.actorOf(Props(new DomainActor()) , "_domain")

  override def receive: Receive = {
    case events: Vector[ParseEvent] => events.foreach(e => self ! e)
    case StartElement("root", attributes: Map[String, String]) => println("Start reading")
    case StartElement("Combustivel", attributes: Map[String, String]) =>
      domainActor ! ("Combustivel", attributes)
    case StartElement("QuantitativaCombustivel", attributes: Map[String, String]) =>
      domainActor ! ("QuantitativaCombustivel", attributes)
    case StartElement("VeiculoEquipamento", attributes: Map[String, String]) =>
      domainActor ! ("VeiculoEquipamento", attributes)
    case other =>
  }

}
