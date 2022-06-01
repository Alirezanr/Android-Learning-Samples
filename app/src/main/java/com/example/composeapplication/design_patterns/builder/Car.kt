package com.example.composeapplication.design_patterns.builder

class Car(
    val wheel: Wheels = SteelWheels(),
    val engine: Engine = ExpensiveEngine(),
    val color: CarColor = BlueCarColor()
) {

    class Builder {
        //fields with initial values
        private var wheels: Wheels = BasicWheels()
        private var engine: Engine = CheepEngine()
        private var carColor: CarColor? = null

        fun setWheels(wheelsType: Wheels): Builder {
            this.wheels = wheelsType
            return this
        }

        fun setEngine(engine: Engine): Builder {
            this.engine = engine
            return this
        }

        fun setCarColor(carColor: CarColor): Builder {
            this.carColor = carColor
            return this
        }

        fun build(): Car {
            /*Do null checks :
            if (wheels == null)
                throw(Exception(message = "Wheels can't be null"))

            if (engine == null)
                throw(Exception(message = "Engine can't be null"))

            if (carColor == null)
                throw(Exception(message = "CarColor can't be null"))*/

            return Car(
                wheel = this.wheels ?: throw(Exception("Wheels can't be null")),
                engine = this.engine ?: throw(Exception("Engine can't be null")),
                color = this.carColor ?: throw(Exception("CarColor can't be null")),
            )
        }
    }
}

fun main() {
    //Classic way of implementing builder pattern
    val car1 = Car.Builder()
        .setEngine(engine = ExpensiveEngine())
        .setWheels(wheelsType = BasicWheels())
        .setCarColor(carColor = WhiteCarColor())
        .build()

    //kotlin efficient way of implementing builder pattern
    val car2 = Car(
        wheel = SteelWheels(),
        engine = ExpensiveEngine(),
        color = WhiteCarColor(),
    )

}