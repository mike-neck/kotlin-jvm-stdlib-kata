package com.example.pets.fixtures

import com.example.pets.Person
import com.example.pets.PetType
import com.example.pets.builder.IterableBuilder
import com.example.pets.builder.iterables
import com.example.pets.builder.person
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import java.lang.reflect.ParameterizedType

class PetDomain: ParameterResolver {

  override fun supportsParameter(
    parameterContext: ParameterContext?, 
    extensionContext: ExtensionContext?): Boolean {
    val parameterType = requireNotNull(requireNotNull(parameterContext).parameter?.type)
    return parameterType == People::class.java
  }

  override fun resolveParameter(
      parameterContext: ParameterContext?, 
      extensionContext: ExtensionContext?): Any {
    requireNotNull(parameterContext)

    return iterables<Person> { 
      add.person { 
        firstName = "Mary"
        lastName = "Smith"
        pet { 
          type = PetType.CAT
          name = "Tabby"
          age = 2
        }
      }
      add.person { 
        firstName = "Bob"
        lastName = "Smith"
        pet { 
          type = PetType.CAT
          name = "Dolly"
          age = 3
        }
        pet { 
          type = PetType.DOG
          name = "Spot"
          age = 2
        }
      }
      add.person { 
        firstName = "Ted"
        lastName = "Smith"
        pet { 
          type = PetType.DOG
          name = "Spike"
          age = 4
        }
      }
      add.person { 
        firstName = "Jake"
        lastName = "Snake"
        pet { 
          type = PetType.SNAKE
          name = "Serpy"
          age = 1
        }
      }
      add.person { 
        firstName = "Barry"
        lastName = "Bird"
        pet { 
          type = PetType.BIRD
          name = "Tweety"
          age = 2
        }
      }
      add.person { 
        firstName = "Terry"
        lastName = "Turtle"
        pet { 
          type = PetType.TURTLE
          name = "Speedy"
          age = 1
        }
      }
      add.person { 
        firstName = "Harry"
        lastName = "Hamster"
        pet { 
          type = PetType.HAMSTER
          name = "Fuzzy"
          age = 1
        }
        pet { 
          type = PetType.HAMSTER
          name = "Wuzzy"
          age = 1
        }
      }
      add.person { 
        firstName = "John"
        lastName = "Doe"
      }
    }.toPeople()
  }

  companion object {
    private fun IterableBuilder<Person>.toPeople(): People = People(this.toMutableList())
  }
}
