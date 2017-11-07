varying vec3 v;


void main(void)
{
	v = vec3(gl_ModelViewMatrix * gl_Vertex);
	
	gl_TexCoord[0] = gl_MultiTexCoord0;
	gl_TexCoord[1] = gl_MultiTexCoord1;
	
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
