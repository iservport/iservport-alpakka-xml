package com.iservport.xml.actor

import akka.actor.{Actor, ActorLogging}
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.{MongoClient, MongoDB}

/**
  * Created by mauriciofernandesdecastro on 29/05/17.
  */
class CollectionActor extends Actor with ActorLogging {

  val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("test")

  val cityColl    = db.getCollection("cityData")
  val entityColl  = db.getCollection("entityData")
  val vehicleColl = db.getCollection("vehicle")
  val usageColl   = db.getCollection("usage")
  val quantityColl   = db.getCollection("quantity")
  val vehicleDataColl   = db.getCollection("vehicleData")

  override def receive: Receive = {
    case d: CityData    => cityColl.save(MongoDBObject(d.json))
    case d: EntityData  => entityColl.save(MongoDBObject(d.json))
    case d: Vehicle     => vehicleColl.save(MongoDBObject(d.json))
    case d: Usage       => usageColl.save(MongoDBObject(d.json))
    case d: Quantity    => quantityColl.save(MongoDBObject(d.json))
    case d: VehicleData => vehicleDataColl.save(MongoDBObject(d.json))
  }

}
