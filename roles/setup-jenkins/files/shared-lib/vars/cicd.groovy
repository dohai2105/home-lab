#!groovy
def call() {


    pipelineVars()

    log.info "cicd JOB_NAME ${JOB_NAME}"
    log.info "cicd pipelineName: ${pipelineYaml.pipelineName}"
    log.info "cicd pipelineYaml: ${pipelineYaml}"
    "${pipelineYaml.pipelineName}"(pipelineYaml)
    
}
call()