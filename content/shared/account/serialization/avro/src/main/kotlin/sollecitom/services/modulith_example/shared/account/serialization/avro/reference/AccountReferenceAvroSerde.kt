package sollecitom.services.modulith_example.shared.account.serialization.avro.reference

import org.apache.avro.generic.GenericRecord
import sollecitom.libs.swissknife.avro.serialization.utils.AvroSerde
import sollecitom.libs.swissknife.avro.serialization.utils.buildRecord
import sollecitom.libs.swissknife.avro.serialization.utils.deserializeWith
import sollecitom.libs.swissknife.avro.serialization.utils.getEnvelope
import sollecitom.services.modulith_example.shared.account.domain.model.reference.AccountReference
import sollecitom.services.modulith_example.shared.account.domain.model.reference.InternalAccountNumber
import sollecitom.services.modulith_example.shared.account.serialization.avro.AccountAvroSchemas

val AccountReference.Companion.avroSchema get() = AccountAvroSchemas.accountReference
val AccountReference.Companion.avroSerde: AvroSerde<AccountReference> get() = AccountReferenceAvroSerde

private object AccountReferenceAvroSerde : AvroSerde<AccountReference> {

    override val schema get() = AccountReference.avroSchema

    override fun serialize(value: AccountReference): GenericRecord = buildRecord {
        val record = when (value) {
            is InternalAccountNumber -> InternalAccountNumber.avroSerde.serialize(value)
        }
        setEnvelope(record)
    }

    override fun deserialize(value: GenericRecord) = value.getEnvelope { branchName, unionRecord ->
        when (branchName) {
            Types.INTERNAL_ACCOUNT_REFERENCE -> unionRecord.deserializeWith(InternalAccountNumber.avroSerde)
            else -> error("Unknown account reference type $branchName")
        }
    }

    private object Types {
        const val INTERNAL_ACCOUNT_REFERENCE = "InternalAccountNumber"
    }
}
