
import play.api.mvc.{Action, Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle,ConfigurationProvider}

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

import   play.api
import             scala.collection.mutable
import             scala.concurrent.Future
import             scala.collection.mutable
import             scala.concurrent.Future
import             scala.collection.mutable
import             scala.concurrent.Future
import             scala.collection.mutable
import             scala.concurrent.Future
import             scala.collection.mutable
import             scala.concurrent.Future
import             scala.collection.mutable
import             scala.concurrent.Future

/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package simple.petstore.api.yaml {

    class SimplePetstoreApiYaml @Inject() (lifecycle: ApplicationLifecycle, config: ConfigurationProvider) extends SimplePetstoreApiYamlBase {
        // ----- Start of unmanaged code area for constructor SimplePetstoreApiYaml
        import     scala.collection.mutable
        import     scala.concurrent.Future
        val cache = mutable.Map.empty[Long, Seq[Pet]]
        // ----- End of unmanaged code area for constructor SimplePetstoreApiYaml
        val findPets = findPetsAction { input: (PetsGetTags, PetsGetLimit) =>
            val (tags, limit) = input
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.findPets
            val pets = tags match {
            case Some(tagList) => cache.collect {
              case (id, pets) => pets.filter(pet => tags.contains(pet.tag))
            }.toSeq.flatten
            case None => Seq.empty[Pet]
          }
          FindPets200(pets)
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.findPets
        }
        val addPet = addPetAction { (pet: NewPet) =>  
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.addPet
            val cachedPet = pet.id match {
              case Some(id) => Pet(id, pet.name, pet.tag)
              case None => Pet(Random.nextLong, pet.name, pet.tag)
            }
            cache.get(cachedPet.id) match {
              case Some(pets) => cache.update(cachedPet.id, pets :+ cachedPet)
              case None => cache.update(cachedPet.id, Seq(cachedPet))
            }
            AddPet200(cachedPet)
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.addPet
        }
        val findPetById = findPetByIdAction { (id: Long) =>  
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.findPetById
            Try(cache(id)) match {
              case Success(pets) => FindPetById200(pets.head)
              case Failure(e: NoSuchElementException) => FindPetByIdNoSuchElementException(e)
              case Failure(e) => FindPetByIdIllegalStateException(new IllegalStateException(e.getMessage))
            }
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.findPetById
        }
        val deletePet = deletePetAction { (id: Long) =>  
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.deletePet
            cache.remove(id)
          DeletePet204()
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.deletePet
        }
    
     // Dead code for absent methodDashboard.pathLevelGet
     /*
            // ----- Start of unmanaged code area for action  Dashboard.pathLevelGet
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.pathLevelGet
     */

    
     // Dead code for absent methodDashboard.methodLevel
     /*
            // ----- Start of unmanaged code area for action  Dashboard.methodLevel
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.methodLevel
     */

    
     // Dead code for absent methodDashboard.pathLevelDelete
     /*
            // ----- Start of unmanaged code area for action  Dashboard.pathLevelDelete
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.pathLevelDelete
     */

    
     // Dead code for absent methodDashboard.postHandler
     /*
            // ----- Start of unmanaged code area for action  Dashboard.postHandler
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.postHandler
     */

    
    }
}
