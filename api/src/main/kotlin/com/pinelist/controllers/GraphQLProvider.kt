package com.pinelist.controllers

import com.google.common.io.Resources
import com.pinelist.domain.pinelist.ListRepository
import com.pinelist.domain.pinelist.PineList
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.IOException
import javax.annotation.PostConstruct
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.beans.factory.annotation.Autowired

@Component
class GraphQLProvider {

    private var graphQL: GraphQL? = null

    @Autowired
    lateinit var dataFetchers: DataFetchers

    @Bean
    fun graphQL(): GraphQL? {
        return graphQL
    }

    @PostConstruct
    @Throws(IOException::class)
    fun init() {
        val graphQLSchema = buildSchema()
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(): GraphQLSchema {
        val url = Resources.getResource("schema.graphqls")
        val sdl = Resources.toString(url, Charsets.UTF_8)
        val typeRegistry = SchemaParser().parse(sdl)

        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("lists", dataFetchers.lists())
                )
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createList", dataFetchers.createList()))
                .build()

        val schemaGenerator = SchemaGenerator()

        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }
}

@Component
class DataFetchers {
    @Autowired
    lateinit var listRepository: ListRepository

    fun lists(): DataFetcher<List<PineList>> {
        return toDataFetcher { listRepository.getLists() }
    }

    fun createList(): DataFetcher<PineList> {
        return toDataFetcher { env -> listRepository.save(env!!.getArgument("name")) }
    }

    fun <T> toDataFetcher(f: (environment: DataFetchingEnvironment?) -> T) : DataFetcher<T> =
            DataFetcher { environment -> f(environment) }
}

//
//@RestController
//class GraphQLController {
//
//    @PostMapping("/graphql")
//    fun myGraphql(@RequestBody request: String): Map<String, Any> {
//
//        val url = Resources.getResource("schema.graphqls")
//        val schema = Resources.toString(url, Charsets.UTF_8)
//
////        val jsonRequest = JSONObject(request)
//
//        val schemaParser = SchemaParser()
//        val typeDefinitionRegistry = schemaParser.parse(schema)
//
//        val runtimeWiring = newRuntimeWiring()
//                .type("Query") { builder -> builder.dataFetcher("lists", StaticDataFetcher(listOf(PineList("asd","sdf")))) }
//                .build()
//
//        val schemaGenerator = SchemaGenerator()
//        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
//        val graphQL = GraphQL.newGraphQL(graphQLSchema).build()
//
//        val executionInput = ExecutionInput.newExecutionInput().query(jsonRequest.getString("query")).build()
//        val executionResult = graphQL.execute(executionInput)
//
//        return executionResult.toSpecification()
//    }
//}