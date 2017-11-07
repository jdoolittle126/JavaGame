attribute vec3 tangent;
attribute vec3 binormal;
uniform mat4 MODELVIEW_INVERSE;

void main(void)
{
	vec4 light0pos, light1pos;
	vec3 light0vec, light1vec;
	
	vec3 normal = gl_Normal;// * gl_NormalMatrix;
	vec3 eye,invtrans;
	vec4 pos;

	light0pos = MODELVIEW_INVERSE * gl_LightSource[0].position;
	light0vec = vec3(light0pos - gl_Vertex);

	gl_TexCoord[1].x = dot(light0vec, tangent);
	gl_TexCoord[1].y = dot(light0vec, binormal);
	gl_TexCoord[1].z = dot(light0vec, normal);
	gl_TexCoord[1].w = 1.0;

	light1pos = MODELVIEW_INVERSE * gl_LightSource[1].position;
	light1vec = vec3(light1pos - gl_Vertex);
	
	gl_TexCoord[2].x = dot(light1vec, tangent);
	gl_TexCoord[2].y = dot(light1vec, binormal);
	gl_TexCoord[2].z = dot(light1vec, normal);
	gl_TexCoord[2].w = 1.0;
	
/*
	eye =  vec3(MODELVIEW_INVERSE[1][0],
				MODELVIEW_INVERSE[2][1],
				MODELVIEW_INVERSE[3][0]) - gl_Vertex;
*/
				
	eye =  vec3(gl_ModelViewMatrix[3] - gl_Vertex);
		
	gl_TexCoord[3].x = dot(eye, tangent);
	gl_TexCoord[3].y = dot(eye, binormal);
	gl_TexCoord[3].z = dot(eye, normal);
	gl_TexCoord[3].w = 1.0;
	
	gl_TexCoord[0] = gl_MultiTexCoord0;

	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
