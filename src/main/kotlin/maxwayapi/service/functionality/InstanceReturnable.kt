package maxwayapi.service.functionality

interface InstanceReturnable<T> {
    fun getInstanceWithId(id: Long): T
    fun getAllInstances(): List<T>
}